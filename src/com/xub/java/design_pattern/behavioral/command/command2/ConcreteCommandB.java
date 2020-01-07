package com.xub.java.design_pattern.behavioral.command.command2;



/**
 * @description: 具体命令
 * @author: 黎清许
 * @create: 2019-12-10 15:44
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandB extends AbstractCommand {
    private CompositeReceiver receiver;

    public ConcreteCommandB() {
        System.out.println("生成命令B");
        this.receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        System.out.println("执行命令B");
        receiver.action2();
    }
}
