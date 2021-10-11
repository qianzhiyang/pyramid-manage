package com.qianzhiyang.service.impl.strategy.pyramid;

import com.github.phantomthief.tuple.FourTuple;
import com.github.phantomthief.tuple.Tuple;
import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.service.AddPyramidStrategy;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-09
 */
public class AddPyramidAlignLeft implements AddPyramidStrategy {
    @Override
    public FourTuple<Long, Long, Long, Long> acquireLeftAndRightOfSon(Pyramid pyramid, String name) {
        long left = pyramid.getLeftVal();
        return Tuple.tuple(left + 1, left + 2, left, left);
    }
}
