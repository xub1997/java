package com.xub.design_pattern.behavioral.observer;

/**
 * @description: 具体对象
 * @author: 黎清许
 * @create: 2019-12-13 10:37
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        if (observerList.size() > 0) {
            for (Observer observer : observerList) {
                observer.response();
            }
        }
    }
}
