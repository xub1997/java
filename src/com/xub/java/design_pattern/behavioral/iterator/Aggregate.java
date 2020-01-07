package com.xub.java.design_pattern.behavioral.iterator;

/**
 * @description: 抽象聚合
 * @author: 黎清许
 * @create: 2019-12-11 14:30
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public interface Aggregate {
    void add(Object obj);
    void remove(Object obj);
    Iterator getIterator();
}
