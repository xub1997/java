package com.xub.java.data.structure.queue;

/**
 * @Name: Queue
 * @Description: TODO
 * @author xub
 * @date 2020/1/7  15:38
 */
public interface Queue<E> {

    /**
     * 入队
     * @param element
     */
    void enqueue(E element);

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 获取队头元素
     * @return
     */
    E getFront();

    /**
     * 获取队尾元素
     * @return
     */
    E getTail();

    /**
     * 获取队列元素数量
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();
}
