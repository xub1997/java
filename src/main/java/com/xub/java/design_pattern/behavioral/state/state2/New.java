package com.xub.java.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:59
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: New
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:59
 */
public class New extends ThreadState {
    public New(ThreadContext context) {
        this.handle(context);
    }

    @Override
    void handle(ThreadContext context) {
        System.out.println("线程新建");
    }
}
