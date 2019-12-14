package com.xub.java.design_pattern.prototype.Prototype2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 15:11
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class PropetypeParam implements Cloneable {
    private String param1;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "PropetypeParam{" +
                "param1='" + param1 + '\'' +
                '}';
    }
}
