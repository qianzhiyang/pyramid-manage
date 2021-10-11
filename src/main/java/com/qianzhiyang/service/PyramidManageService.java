package com.qianzhiyang.service;

import java.util.List;

import com.qianzhiyang.constants.enums.IncludeEnum;
import com.qianzhiyang.entity.Pyramid;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-08
 */
public interface PyramidManageService {

    // 只关心作为someOne的子节点插入，不关心该节点还需要哪些子节点
    boolean addChildPyramid(String name, Pyramid parent, AddPyramidStrategy strategy);

    boolean deleteOnePyramid(Pyramid pyramid);

    // 删除节点和子节点，并且返回所有被删除的子节点，不包括自身
    List<Pyramid> deleteOnePyramidAndFollowers(Pyramid pyramid);

    // 主要用来计算子节点和某一个父节点之间的距离，即，计算第几代徒弟
    List<Pyramid> queryLeaderPyramids(Pyramid pyramid, IncludeEnum includeEnum);

    // 找到集群中某个节点的父节点链路，从跟节点到指定节点
    List<Pyramid> findLeaderTraceInTree(Pyramid pyramid, List<Pyramid> pyramidList);

    // 查找所有子节点，不包含当前节点
    List<Pyramid> queryFollowerPyramids(Pyramid pyramid, IncludeEnum includeEnum);

    List<Pyramid> findAll();
}
