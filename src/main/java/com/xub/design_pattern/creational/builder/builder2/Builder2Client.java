package com.xub.design_pattern.creational.builder.builder2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 14:56
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Builder2Client {
    /**
     * 适用场景：
     *
     * 需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。
     * 需要生成的产品对象的属性相互依赖，需要指定其生成顺序。
     * 对象的创建过程独立于创建该对象的类。在建造者模式中通过引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类和客户类中。
     * 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-------------构造器2模式-------------------------");
        BuilderDemo2 demo1 = new ConcreteBuilder().partA("第一个partA").partB("第一个partB").partC("第一个partC").build();
        System.out.println(demo1.toString());
        BuilderDemo2 demo2 =  new ConcreteBuilder().partA("第二个partA").partB("第二个partB").partC("第二个partC").build();
        System.out.println(demo2.toString());
    }
}
