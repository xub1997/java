package com.xub.design_pattern.behavioral.observer;

/**
 * @description: 具体观察者
 * @author: 黎清许
 * @create: 2019-12-13 10:40
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteObserverA implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者A做出反应");
    }
}
