package com.xub.java.data_structure.queue;

import com.xub.java.data_structure.arrays.Array;

/**
 * @author xub
 * @Name: ArrayQueue
 * @Description: TODO
 * @date 2020/1/7  15:49
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue() {
        this.array = new Array(10);
    }

    public ArrayQueue(int capacity) {
        this.array = new Array(capacity);
    }

    /**
     * 入队
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        array.addLast(element);
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队头元素
     *
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 获取队尾元素
     *
     * @return
     */
    @Override
    public E getTail() {
        return array.getLast();
    }

    /**
     * 获取队列元素数量
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 获取队列的容量
     *
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "array=" + array +
                '}';
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println(arrayQueue.getCapacity());
        System.out.println(arrayQueue.getSize());
        System.out.println(arrayQueue.isEmpty());
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);
            if (i % 3 == 0) {
                arrayQueue.dequeue();
                System.out.println(arrayQueue);
            }
        }
        System.out.println(arrayQueue.getCapacity());
        System.out.println(arrayQueue.getSize());
        System.out.println(arrayQueue.isEmpty());
    }
}
