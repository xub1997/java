package com.xub.data_structure.collection;

import com.xub.java.data_structure.linked_list.LinkedListWithHead;

/**
 * @author xub
 * @Name: LinkedListMap
 * @Description: TODO
 * @date 2020/1/14  22:05
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        private K k;
        private V v;
        private Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public Node(K k) {
            this(k, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + k +
                    ",value=" + v +
                    '}';
        }
    }

    /**
     * 头结点
     */
    private Node dummyHead;

    /**
     * 链表中的元素个数
     */
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public void add(K k, V v) {
        Node node = getNode(k);
        if (node == null) {
            dummyHead.next = new Node(k, v, dummyHead.next);
            size++;
        }else {
            node.v = v;
        }
    }

    @Override
    public V remove(K k) {
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.k.equals(k)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.v;
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        return getNode(k) == null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null ? null : node.v;
    }

    private Node getNode(K k) {
        //从dummyHead.next开始遍历
        Node currentNode = dummyHead.next;
        while (currentNode != null) {
            if (currentNode.k.equals(k)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(k);
        if (node == null) {
            throw new IllegalArgumentException(String.format("%s doesn't exist!",k));
        }else {
            node.v = v;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args){

    }
}
