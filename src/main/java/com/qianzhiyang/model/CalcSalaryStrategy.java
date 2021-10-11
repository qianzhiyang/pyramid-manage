package com.qianzhiyang.model;

import lombok.Data;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@Data
public class CalcSalaryStrategy {
    private long leaderSalaryBefore; // leader的薪水
    private long leaderSalaryAfter; // leader的薪水，修正过的
    private long followerSalaryBefore; // follower的薪水
    private long followerSalaryAfter; // follower的薪水，修正过的
    private String strategyName;
    private String extraData; // 扩展参数
}
