package com.xub.java.design_pattern.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description: 抽象责任链
 * @author: 黎清许
 * @create: 2019-12-05 17:10
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Chain {

    private Integer type;

    protected Chain nextChain;

    public Chain(Integer type) {
        this.type = type;
    }

    protected void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    protected void handle(Responsibility responsibility){
        ResponsibilityType byCode = ResponsibilityType.getByCode(responsibility.getType());
        ResponsibilityType currentType = ResponsibilityType.getByCode(this.type);
        System.out.println(String.format("当前类型为: %s,处理类型为： %s,下一节点为： %s",currentType.getDesc(),byCode.getDesc(),nextChain));
        if (byCode == ResponsibilityType.UNKNOWN) {
            System.out.println("未知类型无法处理");
        } else if (this.type.intValue() == responsibility.getType().intValue()) {
            currentType.getChain().process(responsibility);
        } else {
            if (nextChain != null) {
                System.out.println("--------流转下级处理-------------");
                nextChain.handle(responsibility);
            }
        }
    }

    protected abstract void process(Responsibility responsibility);

}
