package design_pattern.command.command1;

/**
 * @description: 调用者
 * @author: 黎清许
 * @create: 2019-12-10 15:28
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Invoker() {
    }

    public Invoker(Command command) {
        this.command = command;
    }

    public void call(){
        command.execute();
    }
}
