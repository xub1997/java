package com.xub.java.design_pattern.creational.template;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-13 15:03
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class TemplateClient {

    /**
     * 模板方法模式包含以下主要角色。
     *
     * (1) 抽象类（Abstract Class）：负责给出一个算法的轮廓和骨架。它由一个模板方法和若干个基本方法构成。这些方法的定义如下。
     *
     * ① 模板方法：定义了算法的骨架，按某种顺序调用其包含的基本方法。
     *
     * ② 基本方法：是整个算法中的一个步骤，包含以下几种类型。
     * 抽象方法：在抽象类中申明，由具体子类实现。
     * 具体方法：在抽象类中已经实现，在具体子类中可以继承或重写它。
     * 钩子方法：在抽象类中已经实现，包括用于判断的逻辑方法和需要子类重写的空方法两种。
     *
     * (2) 具体子类（Concrete Class）：实现抽象类中所定义的抽象方法和钩子方法，它们是一个顶级逻辑的一个组成步骤。
     *
     * 模板方法模式的主要优点如下：
     *
     * 在父类中形式化地定义一个算法，而由它的子类来实现细节的处理，在子类实现详细的处理算法时并不会改变算法中步骤的执行次序。
     * 模板方法模式是一种代码复用技术，它在类库设计中尤为重要，它提取了类库中的公共行为，将公共行为放在父类中，而通过其子类来实现不同的行为，它鼓励我们恰当使用继承来实现代码复用。
     * 可实现一种反向控制结构，通过子类覆盖父类的钩子方法来决定某一特定步骤是否需要执行。
     * 在模板方法模式中可以通过子类来覆盖父类的基本方法，不同的子类可以提供基本方法的不同实现，更换和增加新的子类很方便，符合单一职责原则和开闭原则。
     *
     * 模板方法模式的主要缺点如下：
     *
     * 需要为每一个基本方法的不同实现提供一个子类，如果父类中可变的基本方法太多，将会导致类的个数增加，系统更加庞大，设计也更加抽象，此时，可结合桥接模式来进行设计。
     *
     * 适用场景：
     *
     * 对一些复杂的算法进行分割，将其算法中固定不变的部分设计为模板方法和父类具体方法，而一些可以改变的细节由其子类来实现。即：一次性实现一个算法的不变部分，并将可变的行为留给子类来实现。
     * 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
     * 需要通过子类来决定父类算法中某个步骤是否执行，实现子类对父类的反向控制。
     *
     * @param args
     */
    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClassA();
        abstractClass.templateMethod();
        AbstractClass abstractClass1 = new ConcreteClassB();
        abstractClass1.templateMethod();
    }
}
