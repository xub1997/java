package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 10:35
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton1 {

    /**
     * 饿汉式单例（线程安全）
     * 优点：简单粗暴、类加载的时候就初始化完成，线程安全；
     * 缺点：类加载的时候就已经完成初始化，如果该对象使用时机比较晚，或者始终没有用到，会造成不必要的内存资源浪费。
     */
    private Singleton1() {
    }

    private static final Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }
}
