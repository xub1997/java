package com.xub.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 18:00
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @author xub
 * @Name: Running
 * @Description: TODO
 * @date 2019/12/24  18:00
 */
public class Running extends ThreadState {

    public Running(ThreadContext context) {
        this.handle(context);
    }

    @Override
    void handle(ThreadContext context) {
        System.out.println("线程运行");
    }
}
