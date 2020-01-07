package com.xub.java.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 18:00
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: Runnable
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  18:00
 */
public class Runnable extends ThreadState {

    public Runnable(ThreadContext context) {
        this.handle(context);
    }

    @Override
    void handle(ThreadContext context) {
        System.out.println("线程就绪");
    }
}
