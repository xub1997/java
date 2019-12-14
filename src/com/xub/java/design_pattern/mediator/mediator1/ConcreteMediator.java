package com.xub.java.design_pattern.mediator.mediator1;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 具体中介
 * @author: 黎清许
 * @create: 2019-12-11 16:11
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteMediator extends Mediator {

    private List<Colleague> colleagueList = new ArrayList<>();

    /**
     * 在中介进行注册，方便后面使用
     *
     * @param colleague
     */
    @Override
    public void register(Colleague colleague) {
        if (!colleagueList.contains(colleague)) {
            colleagueList.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        //对象之间不同调用的处理（修改这里即可对不同的对象进行操作）
        if (colleagueList.size() > 0) {
            for (Colleague value : colleagueList) {
                if (!value.equals(colleague)) {
                    colleague.receive();
                }
            }
        }
    }
}
