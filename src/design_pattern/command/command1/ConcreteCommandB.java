package design_pattern.command.command1;

/**
 * @description: 具体命令
 * @author: 黎清许
 * @create: 2019-12-10 15:44
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteCommandB implements Command {
    private ReceiverA receiverA;

    public ConcreteCommandB() {
        System.out.println("生成命令B");
        this.receiverA = new ReceiverA();
    }

    @Override
    public void execute() {
        System.out.println("执行命令B");
        receiverA.action();
    }
}
