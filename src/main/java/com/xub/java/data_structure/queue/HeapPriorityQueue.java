package com.xub.java.data_structure.queue;

import com.xub.java.data_structure.heap.MaxHeap;

/**
 * @author xub
 * @Name: HeapPriorityQueue
 * @Description: TODO
 * @date 2020/1/15  16:03
 */
public class HeapPriorityQueue<E extends Comparable<E>> implements Queue<E>{

    private MaxHeap<E> maxHeap;

    @Override
    public void enqueue(E element) {
        maxHeap.add(element);
    }

    @Override
    public E dequeue() {
        return maxHeap.poll();
    }

    @Override
    public E getFront() {
        return maxHeap.peek();
    }


    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }


    @Override
    public String toString() {
        return "HeapPriorityQueue{" +
                "maxHeap=" + maxHeap +
                '}';
    }

    public static void main(String[] args){

    }
}
