package com.xub.java.design_pattern.creational.prototype.prototype3;


import java.io.IOException;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 15:46
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Prototype3Client {

    /**
     * 原型模式的主要优点如下：
     *
     * 当创建新的对象实例较为复杂时，使用原型模式可以简化对象的创建过程，通过复制一个已有实例可以提高新实例的创建效率。
     * 扩展性较好，由于在原型模式中提供了抽象原型类，在客户端可以针对抽象原型类进行编程，而将具体原型类写在配置文件中，增加或减少产品类对原有系统都没有任何影响。
     * 原型模式提供了简化的创建结构，工厂方法模式常常需要有一个与产品类等级结构相同的工厂等级结构，而原型模式就不需要这样，
     * 原型模式中产品的复制是通过封装在原型类中的克隆方法实现的，无须专门的工厂类来创建产品。
     * 可以使用深克隆的方式保存对象的状态，使用原型模式将对象复制一份并将其状态保存起来，
     * 以便在需要的时候使用（如恢复到某一历史状态），可辅助实现撤销操作。
     *
     * 原型模式的主要缺点如下：
     *
     * 需要为每一个类配备一个克隆方法，而且该克隆方法位于一个类的内部，当对已有的类进行改造时，需要修改源代码，违背了“开闭原则”。
     * 在实现深克隆时需要编写较为复杂的代码，而且当对象之间存在多重的嵌套引用时，为了实现深克隆，每一层对象对应的类都必须支持深克隆，实现起来可能会比较麻烦。
     *
     * 适用场景：
     *
     * 创建新对象成本较大（如初始化需要占用较长的时间，占用太多的CPU资源或网络资源），
     * 新的对象可以通过原型模式对已有对象进行复制来获得，如果是相似对象，则可以对其成员变量稍作修改。
     * 如果系统要保存对象的状态，而对象的状态变化很小，或者对象本身占用内存较少时，可以使用原型模式配合备忘录模式来实现。
     * 需要避免使用分层次的工厂类来创建分层次的对象，并且类的实例对象只有一个或很少的几个组合状态，
     * 通过复制原型对象得到新实例可能比使用构造函数创建一个新实例更加方便。
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 深拷贝：对值类型的成员变量进行值的复制,对引用类型的成员变量也进行引用对象的复制
         * 第二种实现方式：实现Serializable接口,原型对象里面所有的引用对象均需要实现Serializable接口
         * 在深拷贝的原型模式中，除了复制值类型还能复制引用对象。修改引用对象的值，不会对原来的数据造成影响
         */
        System.out.println("-------------原型模式（深拷贝）----------------");
        Prototype original = new Prototype();
        original.setPrototype1(1);
        original.setPrototype2("测试1");
        PropetypeParam params1 = new PropetypeParam();
        params1.setParam1("测试参数1");
        original.setParam(params1);
        System.out.println(String.format("克隆前: original= %s , original.hasCode = %s, original.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println("------------------------");
        Prototype clone = (Prototype) original.deepClone();
        System.out.println(String.format("克隆后: original= %s , original.hasCode = %s, original.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println(String.format("克隆后: clone= %s , clone.hasCode = %s, clone.param.hasCode = %s", clone.toString(), clone.hashCode(),clone.getParam().hashCode()));
        System.out.println("---------修改引用对象：测试参数1 ->  修改测试参数---------------");
        PropetypeParam cloneParam = clone.getParam();
        cloneParam.setParam1("修改测试参数");
        System.out.println(String.format("修改后: prototype1= %s , prototype1.hasCode = %s, prototype1.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println(String.format("修改后: prototype1= %s , prototype1.hasCode = %s, prototype1.param.hasCode = %s", clone.toString(), clone.hashCode(),clone.getParam().hashCode()));
        System.out.println("------------------------");
    }
}
