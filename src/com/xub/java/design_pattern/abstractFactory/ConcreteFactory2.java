package com.xub.java.design_pattern.abstractFactory;

/**
 * @description: 具体工厂2实现类
 * @author: 黎清许
 * @create: 2019-12-09 11:03
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteFactory2 extends AbstractFactory {
    public ConcreteFactory2() {
        System.out.println("调用工厂2");
    }

    @Override
    AbstractProductA getProductA() {
        System.out.println("调用工厂2的方法生成产品A");
        return new ProductA2();
    }

    @Override
    AbstractProductB getProductB() {
        System.out.println("调用工厂2的方法生成产品B");
        return new ProductB2();
    }
}
