package com.xub.design_pattern.creational.factory;

/**
 * @description: 抽象工厂类
 * @author: 黎清许
 * @create: 2019-12-09 10:17
 * <p>
 * CopyRight &copy; All rights reserved.
 * 抽象工厂模式：
 * JDK（出现的地方）：
 * java.util.Calendar
 * java.util.ResourceBundle
 * java.text.NumberFormat
 * java.nio.charset.Charset
 * java.net.URLStreamHandlerFactory
 * java.util.EnumSet
 * javax.xml.bind.JAXBContext
 **/
public abstract class Factory {

    abstract Product factoryMethod();

    public void doSomething() {
        Product product = factoryMethod();
    }
}
