package com.xub.java.design_pattern.mediator.mediator2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 简单中介模式i
 * @author: 黎清许
 * @create: 2019-12-11 17:25
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class SimpleMediator {
    //单例模式
    private SimpleMediator() {
    }

    private static volatile SimpleMediator simpleMediator;

    public static SimpleMediator getInstance() {
        if (simpleMediator == null) {
            synchronized (SimpleMediator.class) {
                if (simpleMediator == null) {
                    simpleMediator = new SimpleMediator();
                }
            }
        }
        return simpleMediator;
    }

    private List<Colleague> colleagueList = new ArrayList<>();

    public void register(Colleague colleague) {
        if (!colleagueList.contains(colleague)) {
            colleagueList.add(colleague);
        }
    }

    public void relay(Colleague colleague) {
        if (colleagueList.size() > 0) {
            for (Colleague value : colleagueList) {
                if (!value.equals(colleague)) {
                    value.receive();
                }
            }
        }
    }
}
