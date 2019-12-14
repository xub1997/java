package com.xub.java.design_pattern.adapter.objectAdapter;



/**
 * @description: 设配器
 * @author: 黎清许
 * @create: 2019-12-13 16:15
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Adapter implements Target {
    // 适配者是对象适配器的一个属性(通过关联适配者对象实现适配器模式)
    private Adaptee adaptee = new Adaptee();;

    @Override
    public void request() {
        System.out.println("通过适配器调用");
        adaptee.specificRequest();
    }
}
