package com.xub.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description: 具体责任链
 * @author: 黎清许
 * @create: 2019-12-09 16:43
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteChain1 extends Chain {
    public ConcreteChain1(Integer type) {
        super(type);
    }

    @Override
    protected void process(Responsibility responsibility) {
        System.out.println("处理类型1");
    }
}
