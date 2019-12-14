package com.xub.java.design_pattern.adapter.objectAdapter;

/**
 * @description: 目标接口
 * @author: 黎清许
 * @create: 2019-12-13 15:59
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Target {
    default void request(){
        System.out.println("目标方法的一般实现");
    }
}
