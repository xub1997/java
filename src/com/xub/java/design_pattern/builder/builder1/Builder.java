package com.xub.java.design_pattern.builder.builder1;

/**
 * @description: 构造器接口
 * @author: 黎清许
 * @create: 2019-12-09 13:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Builder {
    void setPartA();

    void setPartB();

    void setPartC();

    BuilderDemo1 build();
}
