package com.xub.java.data_structure.queue;

/**
 * @author xub
 * @Name: LinkedListQueue
 * @Description: TODO
 * @date 2020/1/8  11:23
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }

    private Node head;

    private Node tail;

    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /**
     * 入队
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        if (tail == null) {
            tail = new Node(element);
            head = tail;
        } else {
            tail.next = new Node(element);
            tail = tail.next;
        }
        size++;
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from empty queue");
        }
        Node returnNode = head;
        head = head.next;
        returnNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return returnNode.e;
    }

    /**
     * 获取队头元素
     *
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return head.e;
    }

    /**
     * 获取队尾元素
     *
     * @return
     */
    @Override
    public E getTail() {
        return null;
    }

    /**
     * 获取队列元素数量
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> arrayQueue = new LinkedListQueue<>();
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
        System.out.println(arrayQueue.getSize());
        System.out.println(arrayQueue.isEmpty());
    }
}
