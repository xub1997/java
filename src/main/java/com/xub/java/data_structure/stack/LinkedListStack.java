package com.xub.java.data_structure.stack;

import com.xub.java.data_structure.linked_list.LinkedList;

/**
 * @author xub
 * @Name: LinkedListStack
 * @Description: TODO
 * @date 2020/1/8  10:41
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    /**
     * 进栈
     *
     * @param element
     */
    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    /**
     * 出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    @Override
    public E peek() {
        return list.getFirst();
    }

    /**
     * 获取栈中元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 获取栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("LinkedListStack:{top : %s  }",list);
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> arrayStack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }
        for (int i = 0; i < 10; i++) {
            arrayStack.pop();
            System.out.println(arrayStack);
        }
    }
}
