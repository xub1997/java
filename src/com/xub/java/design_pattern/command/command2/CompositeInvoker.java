package com.xub.java.design_pattern.command.command2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 调用者
 * @author: 黎清许
 * @create: 2019-12-10 16:06
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class CompositeInvoker {

    private List<AbstractCommand> children = new ArrayList<>();

    public void add(AbstractCommand command) {
        children.add(command);
    }

    public void remove(AbstractCommand command) {
        children.remove(command);
    }

    public void execute() {
        for (AbstractCommand child : children) {
            child.execute();
        }
    }
}
