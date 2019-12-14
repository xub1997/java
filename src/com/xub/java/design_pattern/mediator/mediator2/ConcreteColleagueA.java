package com.xub.java.design_pattern.mediator.mediator2;

/**
 * @description: 具体同事类A
 * @author: 黎清许
 * @create: 2019-12-11 16:16
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteColleagueA extends Colleague {

    @Override
    public void receive() {
        System.out.println("具体同事类A收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类A发出请求。");
        mediator.relay(this); //请中介者转发
    }
}
