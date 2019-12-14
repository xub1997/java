package com.xub.java.design_pattern.visitor;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-13 13:01
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class VistorClient {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.addElement(new ConcreteElementA());
        objectStructure.addElement(new ConcreteElementB());
        objectStructure.accept(new ConcreteVisitorA());
        System.out.println("----------------------");
        objectStructure.accept(new ConcreteVisitorB());
    }
}
