package design_pattern.singleton;

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
