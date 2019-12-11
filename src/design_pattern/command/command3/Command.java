package design_pattern.command.command3;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 11:09
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Command {

    /**
     * 执行方法
     */
    public abstract void execute();

    /**
     * 撤销执行
     */
    public abstract void undo();
}
