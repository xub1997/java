package com.xub.java.design_pattern.behavioral.state.state1;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:46
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: ConcreteStateA
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:46
 */
public class ConcreteStateA extends State {
    @Override
    void handle(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConcreteStateB());
    }
}
