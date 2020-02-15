package com.xub.design_pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 观察者
 * @author: 黎清许
 * @create: 2019-12-13 10:30
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Subject {
    protected List<Observer> observerList = new ArrayList<>();

    public void registerObserver(Observer observer){
        if(!observerList.contains(observer)){
            System.out.println(String.format("注册观察者: %s",observer.getClass()));
            observerList.add(observer);
        }
    }

    public void removeObserver(Observer observer){
        System.out.println(String.format("移除观察者: %s",observer.getClass()));
        observerList.remove(observer);
    }

    public abstract void notifyObserver();
}
