package design_pattern.template;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-13 14:54
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class AbstractClass {

    public void templateMethod(){
        commonMethod();
        differentMethod1();
        differentMethod2();
    }

    /**
     * 钩子方法
     */
    public void commonMethod(){
        System.out.println("公共方法被调用");
    }

    public abstract void differentMethod1();

    public abstract void differentMethod2();
}
