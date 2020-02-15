package com.xub.java.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 16:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteChain3 extends Chain {
    public ConcreteChain3(Chain nextChain) {
        super(nextChain);
    }

    @Override
    protected void handle(Responsibility responsibility) {
        ResponsibilityType byCode = ResponsibilityType.getByCode(responsibility.getType());
        System.out.println(String.format("当前类型为: %s,处理类型为： %s,下一节点为： %s", ResponsibilityType.TYPE3.getDesc(),byCode.getDesc(),nextChain));
        if (byCode == ResponsibilityType.UNKNOWN) {
            System.out.println("未知类型无法处理");
        } else if (byCode == ResponsibilityType.TYPE3) {
            System.out.println("正在处理类型3");
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
