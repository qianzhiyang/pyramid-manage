package com.qianzhiyang.service.impl.strategy.pyramid;

import com.github.phantomthief.tuple.FourTuple;
import com.github.phantomthief.tuple.Tuple;
import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.service.AddPyramidStrategy;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-08
 */
public class AddPyramidAlignRight implements AddPyramidStrategy {
    @Override
    public FourTuple<Long, Long, Long, Long> acquireLeftAndRightOfSon(Pyramid pyramid, String name) {
        long right = pyramid.getRightVal();
        return Tuple.tuple(right, right + 1, right, right - 1);
    }
}
