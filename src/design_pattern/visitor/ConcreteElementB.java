package design_pattern.visitor;

/**
 * @description: 具体对象
 * @author: 黎清许
 * @create: 2019-12-13 11:49
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB(){
        System.out.println("具体元素B的操作");
    }
}
