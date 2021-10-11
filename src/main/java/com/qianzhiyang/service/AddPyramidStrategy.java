package com.qianzhiyang.service;


import com.github.phantomthief.tuple.FourTuple;
import com.qianzhiyang.entity.Pyramid;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-08
 */
public interface AddPyramidStrategy {
    // 新节点的左右节点，待更新序号的左右起始节点(开区间)。新增节点，会影响所有后续节点
    FourTuple<Long, Long, Long, Long> acquireLeftAndRightOfSon(Pyramid pyramid, String name);
}
