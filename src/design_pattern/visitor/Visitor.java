package design_pattern.visitor;

/**
 * @description: 参观者
 * @author: 黎清许
 * @create: 2019-12-13 11:41
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Visitor {
    void visit(ConcreteElementA elementA);
    void visit(ConcreteElementB elementB);
}
