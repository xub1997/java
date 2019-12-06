package design_pattern.singleton;

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
     */
    private Singleton6(){
    }

    public static Singleton6 getInstance(){
        return Singleton.INSTANCE;
    }

    private static class Singleton{
        private static final Singleton6 INSTANCE = new Singleton6();
    }
}
