package design_pattern.template;

/**
 * @description: 具体模板方法实现
 * @author: 黎清许
 * @create: 2019-12-13 14:58
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteClassA extends AbstractClass {
    @Override
    public void differentMethod1() {
        System.out.println("模板A不同方法1的实现");
    }

    @Override
    public void differentMethod2() {
        System.out.println("模板A不同方法2的实现");
    }
}
