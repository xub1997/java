package com.xub.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 18:20
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @author xub
 * @Name: StateClient
 * @Description: TODO
 * @date 2019/12/24  18:20
 */
public class StateClient {
    public static void main(String[] args) {
        ThreadContext context = new ThreadContext();
        context.start();
        context.getCPU();
        context.suspend();
        context.resume();
        context.getCPU();
        context.stop();
    }
}
