package com.xub.java.design_pattern.behavioral.state.state1;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:47
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: ConcreteStateB
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:47
 */
public class ConcreteStateB extends State {
    @Override
    void handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
