package design_pattern.strategy;

/**
 * @description: 具体策略
 * @author: 黎清许
 * @create: 2019-12-13 15:33
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A方法");
    }
}
