package com.xub.java.design_pattern.factory;

/**
 * @description: 具体产品工厂实现
 * @author: 黎清许
 * @create: 2019-12-09 10:24
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ProductCFactory extends Factory {
    @Override
    Product factoryMethod() {
        System.out.println("产品C工厂的方法");
        return new ProductC();
    }
}
