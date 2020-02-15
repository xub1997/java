package com.xub.design_pattern.behavioral.chainOfResponsibility.chainOfResponsibility1;

/**
 * @description: 抽象责任链
 * @author: 黎清许
 * @create: 2019-12-05 17:10
 * <p>
 * CopyRight &copy; All rights reserved.
 * JDK(出现的地方);
 * java.util.logging.Logger#log()
 * Apache Commons Chain
 * javax.servlet.Filter#doFilter()
 **/
public abstract class Chain {

    protected Chain nextChain;

    public Chain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    protected abstract void handle(Responsibility responsibility);

}
