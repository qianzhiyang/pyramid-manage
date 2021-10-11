package com.qianzhiyang.model;


import java.util.List;

import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.exception.PyramidBuildRelationException;
import com.qianzhiyang.exception.PyramidException;
import com.qianzhiyang.utils.LeaderAndFollowerJudgeUtil;

import lombok.Data;

/**
 * 某个leader和所有的下属节点
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@Data
public class LeaderAndFollowers {
    private Pyramid leader;
    private List<Pyramid> followers;

    private LeaderAndFollowers(Pyramid leader, List<Pyramid> followers) {
        this.leader = leader;
        this.followers = followers;
    }

    public static LeaderAndFollowers of(Pyramid leader, List<Pyramid> followers) throws PyramidException {
        for (Pyramid follower : followers) {
            if (!LeaderAndFollowerJudgeUtil.isLeaderOfFollower(leader, follower)) {
                throw PyramidBuildRelationException.of("first param is not leader of second");
            }
        }
        return new LeaderAndFollowers(leader, followers);
    }
}
