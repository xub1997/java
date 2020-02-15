package com.xub.java.design_pattern.behavioral.command.command1;

/**
 * @description: 命令实现
 * @author: 黎清许
 * @create: 2019-12-10 15:29
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandA implements Command {
    private ReceiverA receiverA;

    public ConcreteCommandA() {
        System.out.println("生成命令A");
        this.receiverA = new ReceiverA();
    }

    @Override
    public void execute() {
        System.out.println("执行命令A");
        receiverA.action();
    }
}
