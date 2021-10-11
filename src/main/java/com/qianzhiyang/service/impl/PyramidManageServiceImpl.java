package com.qianzhiyang.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.phantomthief.tuple.FourTuple;
import com.qianzhiyang.constants.enums.IncludeEnum;
import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.service.AddPyramidStrategy;
import com.qianzhiyang.service.PyramidManageService;
import com.qianzhiyang.service.PyramidRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-08
 */
@Service
@Slf4j
public class PyramidManageServiceImpl implements PyramidManageService {

    @Autowired
    private PyramidRepository pyramidRepository;

    @Override
    public boolean addChildPyramid(String name, Pyramid parent, AddPyramidStrategy addPyramidStrategy) {
        FourTuple<Long, Long, Long, Long> sonPyramid = addPyramidStrategy.acquireLeftAndRightOfSon(parent, name);
        long sonPyramidLeft = sonPyramid.getFirst();
        long sonPyramidRight = sonPyramid.getSecond();
        long pyramidLeftNeedModify = sonPyramid.getThird();
        long pyramidRightNeedModify = sonPyramid.getFourth();

        // 更新后续所有左节点
        pyramidRepository.addAllLeftAfter(pyramidLeftNeedModify, 2);

        // 更新后续所有右节点
        pyramidRepository.addAllRightAfter(pyramidRightNeedModify, 2);

        // 保存新节点
        Pyramid childPyramid = new Pyramid();
        childPyramid.setName(name);
        childPyramid.setLeftVal(sonPyramidLeft);
        childPyramid.setRightVal(sonPyramidRight);
        pyramidRepository.save(childPyramid);
        log.info("saveResult is {}", childPyramid);
        return true;
    }

    @Override
    public boolean deleteOnePyramid(Pyramid pyramid) {
        // 获取左右序号
        long left = pyramid.getLeftVal();
        long right = pyramid.getRightVal();

        // 调整所有子节点
        pyramidRepository.plusLeftAndRightAfterAndBefore(left,  right, 1);

        // 更新后续所有左节点
        pyramidRepository.addAllLeftAfter(right, -2);

        // 更新后续所有右节点
        pyramidRepository.addAllRightAfter(right, -2);

        // 删除节点本身
        pyramidRepository.deleteById(pyramid.getId());
        return true;
    }

    @Override
    public List<Pyramid> deleteOnePyramidAndFollowers(Pyramid pyramid) {
        List<Pyramid> followers = queryFollowerPyramids(pyramid, IncludeEnum.INCLUDE);

        // 获取左右序号
        long right = pyramid.getRightVal();
        long delta = -(pyramid.getRightVal() - pyramid.getLeftVal() + 1);

        // 删除节点本身
        pyramidRepository.deleteAll(followers);
        pyramidRepository.delete(pyramid);

        // 更新后续所有左节点
        pyramidRepository.addAllLeftAfter(right, delta);

        // 更新后续所有右节点
        pyramidRepository.addAllRightAfter(right, delta);

        return followers;
    }

    @Override
    public List<Pyramid> queryLeaderPyramids(Pyramid pyramid, IncludeEnum includeEnum) {
        List<Pyramid> leaderTrace = pyramidRepository.findAllByLeftValIsLessThanAndRightValGreaterThanOrderByLeftVal(pyramid
                .getLeftVal(), pyramid.getRightVal());
        if (includeEnum.equals(IncludeEnum.INCLUDE)) {
            leaderTrace.add(pyramid);
        }
        return leaderTrace;
    }

    @Override
    public List<Pyramid> queryFollowerPyramids(Pyramid pyramid, IncludeEnum includeEnum) {
        List<Pyramid> leaderTrace = pyramidRepository.findAllByLeftValGreaterThanAndRightValIsLessThanOrderByLeftVal(pyramid
                .getLeftVal(), pyramid.getRightVal());
        if (includeEnum.equals(IncludeEnum.INCLUDE)) {
            List<Pyramid> tmpPyramidList = new ArrayList<>(leaderTrace.size() + 1);
            tmpPyramidList.add(pyramid);
            tmpPyramidList.addAll(leaderTrace);
            leaderTrace = tmpPyramidList;
        }
        return leaderTrace;
    }

    @Override
    public List<Pyramid> findLeaderTraceInTree(Pyramid pyramid, List<Pyramid> pyramidList) {
        return pyramidList.stream().filter(pyramid1 -> pyramid1.getLeftVal() < pyramid.getLeftVal()
                && pyramid1.getRightVal() > pyramid.getRightVal()).collect(Collectors.toList());
    }

    @Override
    public List<Pyramid> findAll() {
        List<Pyramid> pyramids = new ArrayList<>();
        for (Pyramid pyramid : pyramidRepository.findAll()) {
            pyramids.add(pyramid);
        }
        return pyramids;
    }
}
