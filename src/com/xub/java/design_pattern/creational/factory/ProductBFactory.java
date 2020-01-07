package com.xub.java.design_pattern.creational.factory;

/**
 * @description: 具体产品工厂实现
 * @author: 黎清许
 * @create: 2019-12-09 10:23
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ProductBFactory extends Factory {
    @Override
    Product factoryMethod() {
        System.out.println("产品B工厂的方法");
        return new ProductB();
    }
}
