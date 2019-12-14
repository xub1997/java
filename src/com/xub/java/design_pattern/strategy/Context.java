package com.xub.java.design_pattern.strategy;

/**
 * @description: 环境类
 * @author: 黎清许
 * @create: 2019-12-13 15:35
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}
