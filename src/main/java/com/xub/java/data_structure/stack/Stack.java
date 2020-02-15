package com.xub.java.data_structure.stack;

/**
 * @Name: Stack
 * @Description: TODO
 * @author xub
 * @date 2020/1/6  23:27
 */
public interface Stack<E> {

    /**
     * 进栈
     * @param element
     */
    void push(E element);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return
     */
    E peek();

    /**
     * 获取栈中元素个数
     * @return
     */
    int getSize();

    /**
     * 获取栈是否为空
     * @return
     */
    boolean isEmpty();

}
