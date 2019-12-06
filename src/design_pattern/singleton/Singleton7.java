package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 11:24
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton7 {
    /**
     * 枚举式单例（线程安全，推荐使用）
     */
    private Singleton7() {
    }

    public static Singleton7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private Singleton7 instance;

        Singleton() {
            instance = new Singleton7();
        }

        public Singleton7 getInstance() {
            return instance;
        }
    }

}
