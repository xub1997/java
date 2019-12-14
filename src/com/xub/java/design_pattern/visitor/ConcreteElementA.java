package com.xub.java.design_pattern.visitor;

/**
 * @description: 具体对象
 * @author: 黎清许
 * @create: 2019-12-13 11:45
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteElementA implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA(){
        System.out.println("具体元素A的操作");
    }
}
