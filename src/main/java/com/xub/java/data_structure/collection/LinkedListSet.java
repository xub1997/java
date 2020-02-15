package com.xub.java.data_structure.collection;

import com.xub.java.data_structure.linked_list.LinkedListWithHead;

/**
 * @author xub
 * @Name: LinkedListSet
 * @Description: TODO
 * @date 2020/1/14  16:20
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedListWithHead linkedListWithHead;

    @Override
    public void add(E e) {
        if(!contains(e)){
            linkedListWithHead.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return linkedListWithHead.contains(e);
    }

    @Override
    public int getSize() {
        return linkedListWithHead.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedListWithHead.isEmpty();
    }
}
