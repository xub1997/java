package com.xub.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ChainOfResponsibilityClient2 {
    /**
     * 职责链模式的主要优点：
     *
     * 对象仅需知道该请求会被处理即可，且链中的对象不需要知道链的结构，由客户端负责链的创建，降低了系统的耦合度
     * 请求处理对象仅需维持一个指向其后继者的引用，而不需要维持它对所有的候选处理者的引用，可简化对象的相互连接
     * 在给对象分派职责时，职责链可以给我们更多的灵活性，可以在运行时对该链进行动态的增删改，改变处理一个请求的职责
     * 新增一个新的具体请求处理者时无须修改原有代码，只需要在客户端重新建链即可，符合 “开闭原则”
     *
     * 职责链模式的主要缺点：
     *
     * 一个请求可能因职责链没有被正确配置而得不到处理
     * 对于比较长的职责链，请求的处理可能涉及到多个处理对象，系统性能将受到一定影响，且不方便调试
     * 可能因为职责链创建不当，造成循环调用，导致系统陷入死循环
     *
     * 适用场景：
     *
     * 有多个对象可以处理同一个请求，具体哪个对象处理该请求待运行时刻再确定，客户端只需将请求提交到链上，而无须关心请求的处理对象是谁以及它是如何处理的
     * 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求
     * 可动态指定一组对象处理请求，客户端可以动态创建职责链来处理请求，还可以改变链中处理者之间的先后次序
     *
     * @param args
     */
    public static void main(String[] args) {
        Chain chain1 = ResponsibilityType.TYPE1.getChain();
        Chain chain2 = ResponsibilityType.TYPE2.getChain();
        chain2.setNextChain(chain1);
        Chain chain3 = ResponsibilityType.TYPE3.getChain();
        chain3.setNextChain(chain2);
        Responsibility responsibility = new Responsibility();
        responsibility.setType(ResponsibilityType.TYPE1.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
        responsibility.setType(ResponsibilityType.TYPE2.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
        responsibility.setType(ResponsibilityType.TYPE3.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
    }
}
