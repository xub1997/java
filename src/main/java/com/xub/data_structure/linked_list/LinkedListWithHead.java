package com.xub.data_structure.linked_list;

/**
 * @author xub
 * @Name: LinkedListWithHead
 * @Description: TODO
 * @date 2020/1/8  8:26
 */
public class LinkedListWithHead<E> {

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

    /**
     * 头结点
     */
    private Node dummyHead;

    /**
     * 链表中的元素个数
     */
    private int size;

    public LinkedListWithHead() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    /**
     * 获取链表中的元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在对应的index位置插入元素
     *
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }
        //将index位置前一个节点的next节点赋值给当前的next节点，把当前节点变成前一个节点的next节点
        Node preNode = dummyHead;
        //找到待插入位置的前一个节点
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        preNode.next = new Node(e, preNode.next);
        size++;
    }

    /**
     * 头部插入元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表的末尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取对应的index位置上的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }
        //从dummyHead.next开始遍历
        Node currentNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.e;
    }

    /**
     * 获取链表中的第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表中的最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改对应的index位置上的元素为e
     *
     * @param index
     * @param e
     * @return
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }
        //从dummyHead.next开始遍历
        Node currentNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.e = e;
    }

    /**
     * 修改链表中的第一个元素为e
     *
     * @return
     */
    public void setFirst(E e) {
        set(0, e);
    }

    /**
     * 修改链表中的最后一个元素为e
     *
     * @return
     */
    public void setLast(E e) {
        set(size - 1, e);
    }

    /**
     * 删除对应的index位置上的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }
        //从dummyHead.next开始遍历
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }
        Node returnNode = preNode.next;
        preNode.next = returnNode.next;
        returnNode.next = null;
        size--;
        return returnNode.e;
    }

    /**
     * 删除链表中的第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除链表中的最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素e
     * @param e
     */
    public void removeElement(E e){
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    /**
     * 查找链表中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        //从dummyHead.next开始遍历
        Node currentNode = dummyHead.next;
        while (currentNode != null) {
            if (currentNode.e.equals(e)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        res.append("size: ").append(size).append(",\t");
        for (Node currentNode = dummyHead.next; currentNode != null; currentNode = currentNode.next) {
            res.append(currentNode + "->");
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListWithHead<Integer> linkedList = new LinkedListWithHead<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.setLast(666);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.removeElement(3);
        System.out.println(linkedList);
    }
}
