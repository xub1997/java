package com.xub.design_pattern.creational.prototype.Prototype2;


/**
 * @description: 原型类
 * @author: 黎清许
 * @create: 2019-12-09 15:35
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Prototype implements Cloneable {
    private Integer prototype1;
    private String prototype2;
    private PropetypeParam param;

    public Integer getPrototype1() {
        return prototype1;
    }

    public void setPrototype1(Integer prototype1) {
        this.prototype1 = prototype1;
    }

    public String getPrototype2() {
        return prototype2;
    }

    public void setPrototype2(String prototype2) {
        this.prototype2 = prototype2;
    }

    public PropetypeParam getParam() {
        return param;
    }

    public void setParam(PropetypeParam param) {
        this.param = param;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        prototype.param = (PropetypeParam) param.clone();
        return prototype;
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "prototype1=" + prototype1 +
                ", prototype2='" + prototype2 + '\'' +
                ", param=" + param +
                '}';
    }
}
