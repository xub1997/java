package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 11:02
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton5 {

    /**
     * 懒汉式单例（double check，线程安全）
     * volatile 防止指令重排
     * synchronized 同步（保证同一时刻只有一个线程进行访问）
     * instance 只需要被实例化一次，之后就可以直接使用了。
     * 加锁操作只需要对实例化那部分的代码进行，只有当 instance 没有被实例化时，才需要进行加锁。
     *
     * 双重校验锁先判断 instance 是否已经被实例化，如果没有被实例化，那么才对实例化语句进行加锁。
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton5() {
    }

    /**
     * instance 采用 volatile 关键字修饰也是很有必要的， instance = new Singleton(); 这段代码其实是分为三步执行：
     *
     * 1、为 instance 分配内存空间
     * 2、初始化 instance
     * 3、将 instance 指向分配的内存地址
     *
     * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，
     * 但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，
     * 此时 T2 调用 getInstance() 后发现 instance 不为空，因此返回 instance，但此时 instance 还未被初始化。
     *
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private static volatile Singleton5 instance;

    public static Singleton5 getInstance() {
        //第一个 if 语句用来避免 instance 已经被实例化之后的加锁操作
        if (instance == null) {
            synchronized (Singleton5.class) {
                //第二个 if 语句进行了加锁，所以只能有一个线程进入，就不会出现 instance == null 时两个线程同时进行实例化操作
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
