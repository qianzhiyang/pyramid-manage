package com.qianzhiyang.service.impl.strategy.salary.calc;

import org.springframework.stereotype.Service;

import com.qianzhiyang.constants.PyramidConstants;
import com.qianzhiyang.model.CalcSalaryContext;
import com.qianzhiyang.service.CalcSalaryService;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
@Service
public class AlibabaCalcSalaryService implements CalcSalaryService {

    // demo
    @Override
    public void calcSalary(CalcSalaryContext calcSalaryContext) {
        long leaderSalary = calcSalaryContext.getCalcSalaryStrategy().getLeaderSalaryBefore();
        long followerSalary = calcSalaryContext.getCalcSalaryStrategy().getFollowerSalaryBefore();
        // 根据alibaba公司的传销规则，计算leader和follower的薪水
        calcSalaryContext.getCalcSalaryStrategy().setLeaderSalaryAfter((long) (leaderSalary + followerSalary * PyramidConstants.RATE_0_2));
        calcSalaryContext.getCalcSalaryStrategy().setFollowerSalaryAfter((long) (followerSalary * PyramidConstants.RATE_0_8));
    }

}
