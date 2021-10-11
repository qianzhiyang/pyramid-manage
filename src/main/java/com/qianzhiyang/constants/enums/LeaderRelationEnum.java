package com.qianzhiyang.constants.enums;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-10
 */
public enum LeaderRelationEnum {
    LEVEL_0(0),
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    ;

    private final int relationGap;

    LeaderRelationEnum(int relationGap) {
        this.relationGap = relationGap;
    }
}
