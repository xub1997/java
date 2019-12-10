package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 10:42
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton3 {

    /**
     * 懒汉式单例：同步对象，线程不安全（未考虑指令重排）
     * 注意：
     * 为了防止单例模式被破坏，我们可以：不实现 Cloneable 接口；或者把 clone 方法改为如下
     *      @Override
     *     protected Object clone() throws CloneNotSupportedException {
     *         return getInstance();
     *     }
     */
    private Singleton3() {
    }

    private static Singleton3 instance;

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

}
