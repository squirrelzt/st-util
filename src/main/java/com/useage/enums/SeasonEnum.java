package com.useage.enums;

/**
 * 类名称: SeasonEnum
 * 类描述: 季度枚举
 * @author squirrel
 * @date 2019-04-23
 */
public enum SeasonEnum {
    /**
     * 春
     */
    SPRING(1),

    /**
     * 夏
     */
    SUMMER(2),

    /**
     * 秋
     */
    AUTUMN(3),

    /**
     * 冬
     */
    WINTER(4);

    private int seq;

    SeasonEnum(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }
}
