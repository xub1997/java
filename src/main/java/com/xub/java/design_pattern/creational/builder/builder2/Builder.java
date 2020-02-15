package com.xub.java.design_pattern.creational.builder.builder2;

/**
 * @description: 构造器接口
 * @author: 黎清许
 * @create: 2019-12-09 13:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Builder {
    Builder partA(String partA);

    Builder partB(String partB);

    Builder partC(String partC);

    BuilderDemo2 build();
}
