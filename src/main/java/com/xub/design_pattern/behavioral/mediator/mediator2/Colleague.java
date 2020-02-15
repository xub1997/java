package com.xub.design_pattern.behavioral.mediator.mediator2;

/**
 * @description: 抽象同事
 * @author: 黎清许
 * @create: 2019-12-11 16:06
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Colleague {
    protected SimpleMediator mediator;

    public SimpleMediator getMediator() {
        return mediator;
    }

    public Colleague() {
        this.mediator = SimpleMediator.getInstance();
        this.mediator.register(this);
    }

    public abstract void receive();

    public abstract void send();
}
