package com.xub.java.design_pattern.creational.builder.builder1;

/**
 * @description: 指挥者
 * @author: 黎清许
 * @create: 2019-12-09 14:13
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public BuilderDemo1 build(){
        builder.setPartA();
        builder.setPartB();
        builder.setPartC();
        return builder.build();
    }
}
