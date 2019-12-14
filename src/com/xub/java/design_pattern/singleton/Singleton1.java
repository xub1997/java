package com.xub.java.design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 10:35
 * <p>
 * CopyRight &copy; All rights reserved.
 * 单例模式：
 *  Examples（案例）：
 *      Logger Classes
 *      Configuration Classes
 *      Accesing resources in shared mode
 *      Factories implemented as Singletons
 *  JDK(出现的地方)：
 *      java.lang.Runtime#getRuntime()
 *      java.awt.Desktop#getDesktop()
 *      [java.lang.System#getSecurityManager()](
 **/
public class Singleton1 {

    /**
     * 饿汉式单例（线程安全）
     * 优点：简单粗暴、类加载的时候就初始化完成，线程安全；
     * 缺点：类加载的时候就已经完成初始化，如果该对象使用时机比较晚，或者始终没有用到，会造成不必要的内存资源浪费（丢失了延迟实例化带来的节约资源的好处）。
     *
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton1() {
    }

    private static final Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }
}
