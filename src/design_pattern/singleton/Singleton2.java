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
