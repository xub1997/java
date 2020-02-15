package com.xub.design_pattern.behavioral.visitor;

/**
 * @description: 具体访问者
 * @author: 黎清许
 * @create: 2019-12-13 11:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteVisitorA implements Visitor{
    @Override
    public void visit(ConcreteElementA elementA) {
        elementA.operationA();
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        elementB.operationB();
    }
}
