package com.xub.java.data_structure.arrays;

import java.util.Arrays;

/**
 * @author xub
 * @Name: Array
 * @Description: TODO
 * @date 2020/1/6  23:27
 */
public class Array<E> {

    private E[] data;
    private int size;
    private int capacity;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        this.capacity = capacity;
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组的容量为10
     */
    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 获取数组中的元素个数
     *
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 获取数组的容量
     *
     * @return
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * 数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组的末尾添加元素
     *
     * @param element
     */
    public void addLast(E element) {
        add(size, element);
    }

    /**
     * 向数组的头部添加元素
     *
     * @param element
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * 在第index个位置插入一个新元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        if (size == data.length) {
            //默认扩容1.5倍
            int newCapacity = capacity + (capacity >> 1);
            resize(newCapacity);
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Index illegal");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }


    /**
     * 将数组空间的容量变成newCapacity大小
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        this.capacity = newCapacity;
    }

    /**
     * 获取数组中的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed.index illegal");
        }
        return data[index];
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取最后一个函数
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改数组中的元素
     *
     * @param index
     * @param element
     */
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed.index illegal");
        }
        data[index] = element;
    }

    /**
     * 查找数组中是否包含元素element
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素element的下标
     *
     * @param element
     * @return
     */
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除数组中对应下标的元素，并返回删除的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed.index illegal");
        }
        E removeEle = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        //防止复杂度震荡
        if (size == capacity / 4 && capacity / 2 != 0) {
            //缩容成原来的1/2
            resize(capacity / 2);
        }
        return removeEle;
    }

    /**
     * 删除数组中第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组中最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除数组中对应的元素
     *
     * @param element
     */
    public void removeElement(E element) {
        int index = find(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d ,capacity =  %d , data = %s\n", size, data.length, Arrays.toString(data)));
        return res.toString();
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array(20);
        System.out.println(array.getCapacity());
//        array.removeFirst();
        System.out.println(array.isEmpty());
        for (int i = 0; i < 20; i++) {
            array.addLast(i);
        }
        System.out.println(array.getCapacity());
        array.addLast(90);
        System.out.println(array.getCapacity());
        System.out.println(array.isEmpty());
        System.out.println(array.toString());
        array.add(1, 100);
        System.out.println(array.toString());
        array.addFirst(-1);
        System.out.println(array.toString());
        System.out.println(array.get(2));
        array.set(1, 666);
        System.out.println(array.toString());
        array.remove(6);
        System.out.println(array.toString());
        for (int i = 0; i < 7; i++) {
            array.removeFirst();
        }
        System.out.println(array.toString());
        array.removeLast();
        System.out.println(array.toString());
    }
}
