package com.qianzhiyang.model;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.qianzhiyang.constants.enums.LeaderRelationEnum;
import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.exception.PyramidBuildRelationException;
import com.qianzhiyang.exception.PyramidException;
import com.qianzhiyang.utils.LeaderAndFollowerJudgeUtil;

import lombok.Data;

/**
 * 某个leader和follower之间的层级关系
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@Data
public class LeaderToFollowerTrace {
    private Pyramid leader;
    private Pyramid follower;
    private LeaderRelationEnum relationEnum;

    private LeaderToFollowerTrace(Pyramid leader, Pyramid follower, LeaderRelationEnum relationEnum) {
        this.leader = leader;
        this.follower = follower;
        this.relationEnum = relationEnum;
    }

    public static LeaderToFollowerTrace of(Pyramid leader, Pyramid follower, List<Pyramid> leaderTrace) throws PyramidException {
        LeaderRelationEnum relationEnum = checkRelationStrict(leader, follower, leaderTrace);
        return new LeaderToFollowerTrace(leader, follower, relationEnum);
    }

    private static LeaderRelationEnum checkRelationStrict(Pyramid leaderPyramid, Pyramid followerPyramid, List<Pyramid> leaderTrace)
            throws PyramidBuildRelationException {
        if (!LeaderAndFollowerJudgeUtil.isLeaderOfFollower(leaderPyramid, followerPyramid)) {
            throw PyramidBuildRelationException.of("first param is not the leader of the second param");
        }

        if (CollectionUtils.isEmpty(leaderTrace) || leaderTrace.size() < 2) {
            throw PyramidBuildRelationException.of("parentTrace is empty or size <= 2");
        }

        long p1 = 0;
        long p2 = 0;

        for (int i = 0; i < leaderTrace.size(); i++) {
            Pyramid pyramid = leaderTrace.get(i);
            if (pyramid.getId().equals(followerPyramid.getId())) {
                p1 = i;
            }
            if (pyramid.getId().equals(leaderPyramid.getId())) {
                p2 = i;
            }
        }
        return LeaderRelationEnum.values()[(int) Math.abs(p1 - p2)];
    }
}
