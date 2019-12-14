package com.xub.java.design_pattern.command.command2;



/**
 * @description: 命令实现
 * @author: 黎清许
 * @create: 2019-12-10 15:29
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandA extends AbstractCommand {
    private CompositeReceiver receiver;

    public ConcreteCommandA() {
        System.out.println("生成命令A");
        this.receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        System.out.println("执行命令A");
        receiver.action1();
    }
}
