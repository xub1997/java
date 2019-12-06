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
     */
    private Singleton5() {
    }

    private static volatile Singleton5 instance;

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
