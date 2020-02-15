package com.xub.java.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:56
 * <p>
 * CopyRight &copy; All rights reserved.
 **/


/**
 * @Name: ThreadState
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:56
 */
public abstract class ThreadState {
    abstract void handle(ThreadContext context);
}
