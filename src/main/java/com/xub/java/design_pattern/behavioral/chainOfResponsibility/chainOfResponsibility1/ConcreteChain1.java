package com.xub.java.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility1;

/**
 * @description: 具体责任链
 * @author: 黎清许
 * @create: 2019-12-09 16:43
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteChain1 extends Chain {
    public ConcreteChain1(Chain nextChain) {
        super(nextChain);
    }

    @Override
    protected void handle(Responsibility responsibility) {
        ResponsibilityType byCode = ResponsibilityType.getByCode(responsibility.getType());
        System.out.println(String.format("当前类型为: %s,处理类型为： %s,下一节点为： %s", ResponsibilityType.TYPE1.getDesc(),byCode.getDesc(),nextChain));
        if (byCode == ResponsibilityType.UNKNOWN) {
            System.out.println("未知类型无法处理");
        } else if (byCode == ResponsibilityType.TYPE1) {
            System.out.println("处理类型1");
        } else {
            if (nextChain == null) {
                System.out.println("没有下级处理");
            } else {
                System.out.println("--------流转下级处理-------------");
                nextChain.handle(responsibility);
            }
        }
    }
}
