package com.xub.java.design_pattern.command.command3;

/**
 * @description: 命令实现
 * @author: 黎清许
 * @create: 2019-12-11 11:13
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandA extends Command {

    private ReceiverA receiverA;

    public ConcreteCommandA() {
        System.out.println("生成命令A");
        this.receiverA = new ReceiverA();
    }

    @Override
    public void execute() {
        receiverA.action();
    }

    @Override
    public void undo() {
        System.out.println("撤销命令A");
        receiverA.undo();
    }
}
