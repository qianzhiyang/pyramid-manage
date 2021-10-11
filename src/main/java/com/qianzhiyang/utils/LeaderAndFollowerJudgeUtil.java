package com.qianzhiyang.utils;

import com.qianzhiyang.entity.Pyramid;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
public class LeaderAndFollowerJudgeUtil {
    public static boolean isLeaderOfFollower(Pyramid leaderPyramid, Pyramid followerPyramid) {
        return leaderPyramid.getLeftVal() < followerPyramid.getLeftVal() && leaderPyramid.getRightVal() > followerPyramid.getRightVal();
    }
}
