package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 10:39
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton2 {

    /**
     * 懒汉式单例（线程不安全：未考虑线程同步，指令重排）
     * 私有静态变量 instance 被延迟实例化，这样做的好处是，如果没有用到该类，那么就不会实例化 instance，从而节约资源。
     *
     * 这个实现在多线程环境下是不安全的，如果多个线程能够同时进入 if (instance == null) ，并且此时 instance 为 null，
     * 那么会有多个线程执行 instance = new Singleton(); 语句，这将导致实例化多次 instance
     *
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton2() {
    }

    private static Singleton2 instance;

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }


}
