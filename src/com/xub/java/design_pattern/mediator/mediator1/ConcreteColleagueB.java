package com.xub.java.design_pattern.mediator.mediator1;

/**
 * @description: 具体同事类B
 * @author: 黎清许
 * @create: 2019-12-11 16:17
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteColleagueB extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类B收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类B发出请求。");
        mediator.relay(this); //请中介者转发
    }
}
