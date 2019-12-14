package com.xub.java.design_pattern.abstractFactory;

/**
 * @description: 具体工厂1实现类
 * @author: 黎清许
 * @create: 2019-12-09 11:02
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteFactory1 extends AbstractFactory {
    public ConcreteFactory1() {
        System.out.println("调用工厂1");
    }

    @Override
    AbstractProductA getProductA() {
        System.out.println("调用工厂1的方法生成产品A");
        return new ProductA1();
    }

    @Override
    AbstractProductB getProductB() {
        System.out.println("调用工厂1的方法生成产品B");
        return new ProductB1();
    }
}
