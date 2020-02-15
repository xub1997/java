package com.xub.java.design_pattern.creational.builder.builder1;

/**
 * @description: 构造器实现类
 * @author: 黎清许
 * @create: 2019-12-09 14:07
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class BuilderA implements Builder {

    private BuilderDemo1 builderDemo1;

    public BuilderA() {
        System.out.println("使用第一种构造器");
        this.builderDemo1 = new BuilderDemo1();
    }

    @Override
    public void setPartA() {
        builderDemo1.setPartA("partA1");
    }

    @Override
    public void setPartB() {
        builderDemo1.setPartB("partB1");
    }

    @Override
    public void setPartC() {
        builderDemo1.setPartC("partC1");
    }

    @Override
    public BuilderDemo1 build() {
        return builderDemo1;
    }
}
