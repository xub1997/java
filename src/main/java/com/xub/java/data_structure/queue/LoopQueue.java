package com.xub.java.data_structure.queue;

import java.util.Arrays;

/**
 * @author xub
 * @Name: LoopQueue 循环队列
 * @Description: TODO
 * @date 2020/1/7  16:09
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    /**
     * 头指针
     */
    private int front;

    /**
     * 尾指针
     */
    private int tail;

    /**
     * 队列元素个数
     */
    private int size;

    /**
     * 队列容量
     */
    private int capacity;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E element) {
        if ((tail + 1) % capacity == front) {
            //默认扩容1.5倍
            int newCapacity = capacity + (capacity >> 1);
            resize(newCapacity);
        }
        data[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        size--;
        if (capacity / 4 == size && capacity / 2 != 0) {
            resize(capacity / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getFront from empty queue");
        }
        return data[front];
    }

    @Override
    public E getTail() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getTail from empty queue");
        }
        return data[tail];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * 获取队列的容量
     *
     * @return
     */
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    /**
     * 计算当前元素个数
     * @return
     */
    public int size(){
        //如果不记录size值，可以通过(tail + capacity - front) % capacity
        //可以看做有tail-front为元素数量，但tail可能在front前面，所以可以想象成后接一个capacity的队列，减去front再对capacity求余
        return (tail + capacity - front) % capacity;
    }

    /**
     * 将数组空间的容量变成newCapacity大小
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % capacity];
        }
        data = newData;
        front = 0;
        tail = size;
        this.capacity = newCapacity;
    }

    @Override
    public String toString() {
        return "LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", front=" + front +
                ", tail=" + tail +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println(loopQueue.getCapacity());
        System.out.println(loopQueue.getSize());
        System.out.println(loopQueue.size());
        System.out.println(loopQueue.isEmpty());
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue.getFront());
            System.out.println(loopQueue.getTail());
            System.out.println(loopQueue);
//            if (i % 2 == 0) {
//                loopQueue.dequeue();
//                if(!loopQueue.isEmpty()){
//                    System.out.println(loopQueue.getFront());
//                    System.out.println(loopQueue.getTail());
//                }
//                System.out.println(loopQueue);
//            }
        }
        System.out.println(loopQueue.isEmpty());
        System.out.println(loopQueue.getFront());
        System.out.println(loopQueue.getTail());
        System.out.println(loopQueue.getCapacity());
        System.out.println(loopQueue.getSize());
        System.out.println(loopQueue.size());
    }
}
