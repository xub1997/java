package com.xub.java.design_pattern.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 16:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteChain3 extends Chain {
    public ConcreteChain3(Integer type) {
        super(type);
    }

    @Override
    protected void process(Responsibility responsibility) {
        System.out.println("处理类型3");
    }
}
