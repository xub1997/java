package com.xub.java.data_structure.linked_list;

/**
 * @author xub
 * @Name: LinkedList
 * @Description: TODO
 * @date 2020/1/7  23:20
 */
public class LinkedList<E> {

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
    private Node head;

    /**
     * 链表中的元素个数
     */
    private int size;

    public LinkedList() {
        this.head = null;
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
        if (index == 0) {
            addFirst(e);
        } else {
            Node pre = head;
            //找到待插入位置的前一个节点
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            pre.next = new Node(e, pre.next);
            size++;
        }
    }

    /**
     * 头部插入元素
     *
     * @param e
     */
    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        //将head赋值给当前的next节点，把当前节点变成头结点
        head = new Node(e, head);
        size++;
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
        //从head开始遍历
        Node currentNode = head;
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
        //从head开始遍历
        Node currentNode = head;
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
            throw new IllegalArgumentException("List is empty!");
        }
        Node returnNode = new Node();
        if (index == 0) {
            returnNode.e = removeFirst();
        } else {
            Node preNode = head;
            //找到待插入位置的前一个节点
            for (int i = 0; i < index - 1; i++) {
                preNode = preNode.next;
            }
            returnNode = preNode.next;
            preNode.next = returnNode.next;
            returnNode.next = null;
            size--;
        }
        return returnNode.e;
    }

    /**
     * 删除链表中的第一个元素
     *
     * @return
     */
    public E removeFirst() {
        Node returnNode = head;
        head = head.next;
        size--;
        return returnNode.e;
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
        if(isEmpty()){
            throw new IllegalArgumentException("List is empty!");
        }
        //从head开始遍历
        Node delNode = head;
        Node preNode = head;
        while (delNode != null) {
            if (delNode.e.equals(e)) {
                if(preNode.equals(delNode)){
                    head = head.next;
                }else {
                    preNode.next = delNode.next;
                    delNode.next = null;
                }
                size --;
            }
            preNode = delNode;
            delNode = delNode.next;
        }


    }

    /**
     * 查找链表中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        //从head开始遍历
        Node currentNode = head;
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
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            res.append(currentNode + "->");
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(5, 666);

        System.out.println(linkedList);
        linkedList.set(2, 999);

        System.out.println(linkedList);
        linkedList.setFirst(111);

        System.out.println(linkedList);
        linkedList.setLast(888);
        System.out.println("===========");
        System.out.println(linkedList.getLast());
        System.out.println(linkedList);
        System.out.println(linkedList.removeFirst());

        System.out.println(linkedList);
        System.out.println(linkedList.removeLast());

        System.out.println(linkedList);
        System.out.println(linkedList.remove(2));

        System.out.println(linkedList);
        linkedList.removeElement(999);
        System.out.println(linkedList);
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        System.out.println("======");
        linkedList.removeElement(0);
        System.out.println(linkedList);
        linkedList.removeElement(3);
        System.out.println(linkedList);
    }

}
