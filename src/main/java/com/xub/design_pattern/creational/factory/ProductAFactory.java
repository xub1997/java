package com.xub.design_pattern.creational.factory;

/**
 * @description: 具体产品工厂实现
 * @author: 黎清许
 * @create: 2019-12-09 10:22
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ProductAFactory extends Factory {
    @Override
    Product factoryMethod() {
        System.out.println("产品A工厂的方法");
        return new ProductA();
    }
}
