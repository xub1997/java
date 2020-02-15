package com.xub.design_pattern.behavioral.command.command3;

/**
 * @description: 接收者/实现者
 * @author: 黎清许
 * @create: 2019-12-10 15:42
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ReceiverA extends ReceiverOriginator {

    public ReceiverA() {
        this.setState(new State("CommandA process init"));
    }

    public void action() {
        System.out.println(String.format("原本的状态： %s",toString()));
        System.out.println("接收者A的action()方法被调用...");
        setMemento(createMemento());
        setState(new State("CommandA process success"));
        System.out.println(String.format("执行后的状态： %s",toString()));
    }

    public void undo() {
        System.out.println("接收者A的undo()方法被调用...");
        restore(getMemento());
        System.out.println(String.format("回滚后的状态： %s",toString()));
    }
}
