package com.xub.java.design_pattern.visitor;

/**
 * @description: 对象
 * @author: 黎清许
 * @create: 2019-12-13 11:45
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Element {
    void accept(Visitor visitor);
}
