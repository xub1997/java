package com.xub.java.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 18:00
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: Blocked
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  18:00
 */
public class Blocked extends ThreadState {
    public Blocked(ThreadContext context) {
        this.handle(context);
    }

    @Override
    void handle(ThreadContext context) {
        System.out.println("线程阻塞");
    }
}
