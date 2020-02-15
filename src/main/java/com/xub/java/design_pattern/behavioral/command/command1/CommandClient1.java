package com.xub.java.design_pattern.behavioral.command.command1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-10 15:52
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class CommandClient1 {
    /**
     * 普通命令模式：
     * 调用者调用具体命令，命令是什么就执行什么
     * @param args
     */
    public static void main(String[] args) {
        Command command1 = new ConcreteCommandA();
        Invoker invoker = new Invoker();
        invoker.setCommand(command1);
        System.out.println("客户访问具体命令A的execute()方法...");
        invoker.call();
        System.out.println("---------------------");
        Command command2 = new ConcreteCommandB();
        invoker.setCommand(command2);
        System.out.println("客户访问具体命令B的execute()方法...");
        invoker.call();
    }
}
