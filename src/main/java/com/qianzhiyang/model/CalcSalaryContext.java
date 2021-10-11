package com.qianzhiyang.model;

import java.util.Map;

import lombok.Data;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@Data
public class CalcSalaryContext {
    private LeaderAndFollowers leaderAndFollowers;
    private LeaderToFollowerTrace leaderToFollowerTrace;
    private CalcSalaryStrategy calcSalaryStrategy;
    private Map<String, String> extraData;
}
