package design_pattern.iterator;

import java.util.List;

/**
 * @description: 具体迭代器
 * @author: 黎清许
 * @create: 2019-12-11 14:21
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ConcreteIterator implements Iterator {

    private List<Object> list;

    private int cursor = -1;


    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object next() {
        return this.hasNext() ? list.get(++cursor) : null;
    }

    @Override
    public boolean hasNext() {
        return cursor < list.size() - 1;
    }
}
