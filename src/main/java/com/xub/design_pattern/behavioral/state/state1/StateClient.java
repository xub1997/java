package com.xub.design_pattern.behavioral.state.state1;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @author xub
 * @Name: StateClient
 * @Description: TODO
 * @date 2019/12/24  17:51
 */
public class StateClient {
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }

}
