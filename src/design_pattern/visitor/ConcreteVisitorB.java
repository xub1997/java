package design_pattern.visitor;

/**
 * @description: 具体参观者
 * @author: 黎清许
 * @create: 2019-12-13 11:58
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteVisitorB implements Visitor {
    @Override
    public void visit(ConcreteElementA elementA) {
        elementA.operationA();
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        elementB.operationB();
    }
}
