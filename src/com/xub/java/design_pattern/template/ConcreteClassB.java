package com.xub.java.design_pattern.template;

/**
 * @description: 具体模板方法实现
 * @author: 黎清许
 * @create: 2019-12-13 15:01
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteClassB extends AbstractClass {
    @Override
    public void differentMethod1() {
        System.out.println("模板B不同方法1的实现");
    }

    @Override
    public void differentMethod2() {
        System.out.println("模板B不同方法2的实现");
    }
}
