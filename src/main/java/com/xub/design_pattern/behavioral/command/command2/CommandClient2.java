package com.xub.design_pattern.behavioral.command.command2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-10 16:10
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class CommandClient2 {

    /**
     * 命令模式与组合模式联合使用，这就构成了宏命令模式，也叫组合命令模式。宏命令包含了一组命令，
     * 它充当了具体命令与调用者的双重角色，执行它时将递归调用它所包含的所有命令
     * @param args
     */
    public static void main(String[] args) {
        AbstractCommand cmd1=new ConcreteCommandA();
        AbstractCommand cmd2=new ConcreteCommandB();
        CompositeInvoker ir=new CompositeInvoker();
        ir.add(cmd1);
        ir.add(cmd2);
        System.out.println("客户访问调用者的execute()方法...");
        ir.execute();
    }
}
