package com.xub.java.design_pattern.creational.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 11:37
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton8 {
    /**
     * 饿汉式变种（静态代码块实例化对象，线程安全）
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton8() {
    }

    private static Singleton8 instance;

    static {
        instance = new Singleton8();
    }

    public static Singleton8 getInstance() {
        return instance;
    }
}
