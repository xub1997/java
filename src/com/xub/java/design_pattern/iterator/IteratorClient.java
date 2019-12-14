package com.xub.java.design_pattern.iterator;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 14:34
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class IteratorClient {

    /**
     * 迭代器模式主要包含以下角色。
     * 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
     * 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
     * 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、next() 等方法。
     * 具体迭代器（ConcreteIterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
     *
     * 迭代器模式的主要优点如下：
     *
     * 它支持以不同的方式遍历一个聚合对象，在同一个聚合对象上可以定义多种遍历方式。在迭代器模式中只需要用一个不同的迭代器来替换原有迭代器即可改变遍历算法，
     * 我们也可以自己定义迭代器的子类以支持新的遍历方式。
     * 迭代器简化了聚合类。由于引入了迭代器，在原有的聚合对象中不需要再自行提供数据遍历等方法，这样可以简化聚合类的设计。
     * 在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便，无须修改原有代码，满足 “开闭原则” 的要求。
     *
     * 迭代器模式的主要缺点如下：
     *
     * 由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。
     * 抽象迭代器的设计难度较大，需要充分考虑到系统将来的扩展，例如JDK内置迭代器Iterator就无法实现逆向遍历，如果需要实现逆向遍历，
     * 只能通过其子类ListIterator等来实现，而ListIterator迭代器无法用于操作Set类型的聚合对象。在自定义迭代器时，创建一个考虑全面的抽象迭代器并不是件很容易的事情。
     *
     * 适用场景:
     *
     * 访问一个聚合对象的内容而无须暴露它的内部表示。将聚合对象的访问与内部数据的存储分离，使得访问聚合对象时无须了解其内部实现细节。
     * 需要为一个聚合对象提供多种遍历方式。
     * 为遍历不同的聚合结构提供一个统一的接口，在该接口的实现类中为不同的聚合结构提供不同的遍历方式，而客户端可以一致性地操作该接口。
     *
     * JDK（出现的地方）：
     * java.util.Iterator
     * java.util.Enumeration
     *
     * @param args
     */
    public static void main(String[] args) {
        Aggregate aggregate=new ConcreteAggregate();
        aggregate.add("张三");
        aggregate.add("李四");
        aggregate.add("王五");
        aggregate.add("赵六");
        System.out.print("聚合的内容有：");
        Iterator it=aggregate.getIterator();
        while(it.hasNext())
        {
            Object ob=it.next();
            System.out.print(ob.toString()+"\t");
        }
        System.out.println();
        aggregate.remove("李四");
        System.out.println("--------移除李四---------");
        System.out.print("聚合的内容有：");
        it=aggregate.getIterator();
        while(it.hasNext())
        {
            Object ob=it.next();
            System.out.print(ob.toString()+"\t");
        }
    }
}
