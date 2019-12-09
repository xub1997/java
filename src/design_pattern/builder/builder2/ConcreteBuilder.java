package design_pattern.builder.builder2;

/**
 * @description: 构造器实现类
 * @author: 黎清许
 * @create: 2019-12-09 14:07
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteBuilder implements Builder {

    private BuilderDemo2 builderDemo2;

    public ConcreteBuilder() {
        System.out.println("使用第一种构造器");
        this.builderDemo2 = new BuilderDemo2();
    }

    @Override
    public Builder partA(String partA) {
        builderDemo2.setPartA(partA);
        return this;
    }

    @Override
    public Builder partB(String partB) {
        builderDemo2.setPartB(partB);
        return this;
    }

    @Override
    public Builder partC(String partC) {
        builderDemo2.setPartC(partC);
        return this;
    }


    @Override
    public BuilderDemo2 build() {
        return builderDemo2;
    }
}
