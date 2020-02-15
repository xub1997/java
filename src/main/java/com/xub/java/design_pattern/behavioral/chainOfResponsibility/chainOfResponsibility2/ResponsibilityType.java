package com.xub.java.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 16:34
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public enum ResponsibilityType {
    /**
     * 类型1
     */
    TYPE1(1, "类型1",new ConcreteChain1(1)),
    /**
     * 类型2
     */
    TYPE2(2, "类型2",new ConcreteChain2(2)),
    /**
     * 类型3
     */
    TYPE3(3, "类型3",new ConcreteChain3(3)),
    /**
     * 未知类型
     */
    UNKNOWN(-1, "未知类型",null);

    private Integer code;
    private String desc;
    private Chain chain;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Chain getChain() {
        return chain;
    }

    ResponsibilityType(Integer code, String desc, Chain chain) {
        this.code = code;
        this.desc = desc;
        this.chain = chain;
    }

    public static ResponsibilityType getByCode(Integer code) {
        ResponsibilityType[] values = ResponsibilityType.values();
        for (ResponsibilityType value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
