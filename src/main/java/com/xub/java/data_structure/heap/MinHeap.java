package com.xub.java.data_structure.heap;

import com.xub.java.data_structure.arrays.Array;

import java.util.Random;

/**
 * @author xub
 * @Name: MinHeap
 * @Description: TODO
 * @date 2020/1/15  17:07
 */
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MinHeap() {
        data = new Array<>();
    }

    /**
     * 返回堆中的元素个数
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值, 表示堆中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftDown(data.getSize() - 1);
    }

    /**
     * 堆中元素下浮
     *
     * @param k
     */
    private void siftDown(int k) {
        //k不能是根节点，并且k的值要比父节点的值小
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
            //元素与父节点元素进行交换
            swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 交换索引为i、j的值
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    /**
     * 查看堆顶元素
     *
     * @return
     */
    public E peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("MinHeap is empty!");
        }
        return data.get(0);
    }

    /**
     * 移除堆顶元素
     *
     * @return
     */
    public E poll() {
        if (isEmpty()) {
            throw new IllegalArgumentException("MinHeap is empty!");
        }
        E e = peek();
        swap(0, data.getSize() - 1);
        data.removeLast();
        siftUp(0);
        return e;
    }

    /**
     * 堆中元素上浮
     *
     * @param k
     */
    private void siftUp(int k) {
        //leftChild存在
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            //rightChild存在,且值大于leftChild的值
            if (j + 1 < data.getSize() &&
                    data.get(j).compareTo(data.get(j + 1)) > 0) {
                //data[j]是leftChild和rightChild中最小的
                j = rightChild(k);
            }
            //如果当前的值比左右节点的最小值要小就退出循环
            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }
            //否则交换数据，并继续下浮
            swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最小元素，并且替换成元素e
     * @param e
     * @return
     */
    public E replace(E e){
        E ret = peek();
        data.set(0, e);
        siftUp(0);
        return ret;
    }

    @Override
    public String toString() {
        return "MinHeap{" +
                "data=" + data +
                '}';
    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            heap.add(random.nextInt(10));
            System.out.println(heap);
        }
        System.out.println(heap.poll());
        System.out.println(heap);
        System.out.println(heap.replace(0));
        System.out.println(heap);
        int[] arr = new int[heap.size()];
        int size = heap.size();
        for (int i = 0; i <size ; i++) {
            arr[i] = heap.poll();
        }
        System.out.println();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new RuntimeException("Error");
            }
        }
        System.out.println("ok");
    }
}
