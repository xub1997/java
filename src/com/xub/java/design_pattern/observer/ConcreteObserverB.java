package com.xub.java.design_pattern.observer;

/**
 * @description: 具体观察者
 * @author: 黎清许
 * @create: 2019-12-13 10:40
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteObserverB implements Observer{
    @Override
    public void response() {
        System.out.println("具体观察者B做出反应");
    }
}
