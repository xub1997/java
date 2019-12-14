package com.xub.java.design_pattern.command.command3;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 11:27
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class CommandClient3 {
    /**
     * 命令模式与备忘录模式联合使用，即可实现命令的回滚操作
     * @param args
     */
    public static void main(String[] args) {
        Command command1 = new ConcreteCommandA();
        Invoker invoker = new Invoker();
        invoker.setCommand(command1);
        System.out.println("客户访问具体命令A的execute()方法...");
        invoker.call();
        System.out.println("客户访问具体命令A的undo()方法...");
        invoker.cancel();
        System.out.println("---------------------");
        Command command2 = new ConcreteCommandB();
        invoker.setCommand(command2);
        System.out.println("客户访问具体命令B的execute()方法...");
        invoker.call();
        System.out.println("客户访问具体命令B的undo()方法...");
        invoker.cancel();
    }
}
