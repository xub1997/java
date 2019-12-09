package design_pattern.builder.builder1;

/**
 * @description: 构造器实现类
 * @author: 黎清许
 * @create: 2019-12-09 14:24
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class BuilderB implements Builder {
    private BuilderDemo1 builderDemo1;

    public BuilderB() {
        System.out.println("使用第二种构造器");
        this.builderDemo1 = new BuilderDemo1();
    }

    @Override
    public void setPartA() {
        builderDemo1.setPartA("partA2");
    }

    @Override
    public void setPartB() {
        builderDemo1.setPartB("partB2");
    }

    @Override
    public void setPartC() {
        builderDemo1.setPartC("partC2");
    }

    @Override
    public BuilderDemo1 build() {
        return builderDemo1;
    }
}
