package com.xub.java.design_pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 具体聚合
 * @author: 黎清许
 * @create: 2019-12-11 14:32
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteAggregate implements Aggregate {
    private List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        if (list.size() > 0) list.remove(obj);
    }

    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}
