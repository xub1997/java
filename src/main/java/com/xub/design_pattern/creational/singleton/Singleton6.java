package com.xub.design_pattern.creational.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 11:06
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton6 {

    /**
     * 静态内部类（线程安全，延迟加载）
     * 当 Singleton 类被加载时，静态内部类 SingletonHolder 没有被加载进内存。
     * 只有当调用 getInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，
     * 此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
     *
     * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton6(){
    }

    public static Singleton6 getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        private static final Singleton6 INSTANCE = new Singleton6();
    }
}
