package design_pattern.command.command3;

import design_pattern.command.command1.ReceiverA;

/**
 * @description: 命令实现
 * @author: 黎清许
 * @create: 2019-12-11 11:16
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandB extends Command{

    private ReceiverB receiverB;

    public ConcreteCommandB() {
        System.out.println("生成命令B");
        this.receiverB = new ReceiverB();
    }

    @Override
    public void execute() {
        receiverB.action();
    }

    @Override
    public void undo() {
        System.out.println("撤销命令B");
        receiverB.undo();
    }
}
