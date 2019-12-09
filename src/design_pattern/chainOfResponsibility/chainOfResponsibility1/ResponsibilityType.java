package design_pattern.chainOfResponsibility.chainOfResponsibility1;

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
    TYPE1(1, "类型1"),
    /**
     * 类型2
     */
    TYPE2(2, "类型2"),
    /**
     * 类型3
     */
    TYPE3(3, "类型3"),
    /**
     * 未知类型
     */
    UNKNOWN(-1, "未知类型");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponsibilityType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
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
