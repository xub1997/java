package com.xub.design_pattern.creational.prototype.prototype3;

import java.io.Serializable;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 15:46
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class PropetypeParam implements Serializable {
    private String param1;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Override
    public String toString() {
        return "PropetypeParam{" +
                "param1='" + param1 + '\'' +
                '}';
    }
}
