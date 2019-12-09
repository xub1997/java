package design_pattern.singleton;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 10:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Singleton4 {

    /**
     * 懒汉式单例：同步代码块，线程安全
     * 优点：延迟初始化，避免了不必要的内存开销,且线程安全；
     * 缺点：效率偏低，每次获取实例都进行同步锁，事实上只需要在第一次new对象的时候同步锁就行了，后续想获取实例可以直接返回。
     * 该方法有性能问题，不推荐使用
     */
    private Singleton4(){
    }

    private static Singleton4 instance;

    public static synchronized Singleton4 getInstance(){
        if(instance == null){
            instance = new Singleton4();
        }
        return instance;
    }

}
