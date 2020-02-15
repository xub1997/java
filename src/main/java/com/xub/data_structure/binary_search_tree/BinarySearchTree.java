package com.xub.data_structure.binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @author xub
 * @Name: BinarySearchTree
 * @Description: TODO
 * @date 2020/1/8  20:28
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 非递归实现添加元素
     *
     * @param e
     */
    public void addNR(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        } else {
            Node node = root;
            while (node != null) {
                //不包含重复元素
                if (node.e.equals(e)) {
                    return;
                } else if (node.e.compareTo(e) > 0 && node.left == null) {
                    //比当前节点小，且左子树为空
                    node.left = new Node(e);
                    size++;
                    return;
                } else if (node.e.compareTo(e) < 0 && node.right == null) {
                    //比当前节点大，且右子树为空
                    node.right = new Node(e);
                    size++;
                    return;
                } else if (node.e.compareTo(e) > 0 && node.left != null) {
                    //比当前节点小，且左子树不为空
                    //与右子树比较
                    node = node.left;
                } else {
                    //比当前节点大，且右子树不为空
                    node = node.right;
                }
            }
        }
//        if (root == null) {
//            root = new Node(e);
//            size++;
//            return;
//        }
//
//        Node node = null;
//        Node subtree = root;
//
//        //在左子树或右子树为空的时候跳出循环，进行赋值
//        while (subtree != null) {
//            //不包含重复元素
//            if (subtree.e.equals(e)) {
//                return;
//            }
//            //记录当前节点
//            node = subtree;
//
//            if (subtree.e.compareTo(e) > 0) {
//                //比当前节点的元素小
//                subtree = subtree.left;
//            } else {
//                //比当前节点的元素大
//                subtree = subtree.right;
//            }
//        }
//        if (node.e.compareTo(e) > 0) {
//            node.left = new Node(e);
//            size++;
//        } else {
//            node.right = new Node(e);
//            size++;
//        }
    }

    /**
     * 向二叉查找树中添加新的元素e
     *
     * @param e
     */
    //原始版
//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }
    //改进版
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二叉查找树中插入元素e，递归算法
     *
     * @param node
     * @param e
     */
    //原始版
//    private void add(Node node, E e) {
//        if (e.equals(node.e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) >= 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }
    //改进版
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 二分查找树是否包含对应元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 看以root为根的二分查找树是否包含元素e，递归算法
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 二叉查找树的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 以node为根的二分查找树的前序遍历，递归算法
     *
     * @param node
     * @return
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        //访问节点
        System.out.println(node.e);
        //访问节点左子树
        preOrder(node.left);
        //访问节点右子树
        preOrder(node.right);
    }

    /*
     * 非递归的前序遍历,巧妙使用栈来实现打印结点的顺序
     * 将根节点入栈，定义currentNode接收出栈结点
     * 当栈不为null时,打印currentNode.e，依次入栈cur的右子树，左子树,出栈时，依次打印栈的左子树，右子树
     */
    public void preOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            //访问元素
            System.out.println(currentNode.e);

            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    /**
     * 二叉查找树的中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 以node为根的二分查找树的中序遍历，递归算法
     *
     * @param node
     * @return
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        //访问节点左子树
        inOrder(node.left);
        //访问节点
        System.out.println(node.e);
        //访问节点右子树
        inOrder(node.right);
    }

    /*
     * 非递归的中序遍历,巧妙使用栈来实现打印结点的顺序
     * 将根节点入栈，定义node接收出栈结点
     * 当栈不为null时,将所有的左孩子都入栈，然后进行出栈操作，再将右孩子入栈，直到所有的元素都出栈为止
     */
    public void inOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        //辅助节点
        Node node = root;
        stack.push(node);
        while (!stack.isEmpty()) {
            //只要你有左孩子，就将左孩子压入栈中
            if (node != null && node.left != null) {
                stack.push(node.left);
                node = node.left;
            } else {
                //弹出栈顶节点
                node = stack.pop();
                //访问
                System.out.println(node.e);
                //如果栈点元素有右孩子的话，将有节点压入栈中
                if (node != null && node.right != null) {
                    stack.push(node.right);
                    node = node.right;
                } else {
                    //node=stack.pop;已经访问过node了，node设置为null
                    node = null;
                }

            }
        }

    }

    /**
     * 二叉查找树的后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 以node为根的二分查找树的后序遍历，递归算法
     *
     * @param node
     * @return
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        //访问节点左子树
        postOrder(node.left);
        //访问节点右子树
        postOrder(node.right);
        //访问节点
        System.out.println(node.e);
    }

    /*
     * 非递归的后序遍历,巧妙使用栈来实现打印结点的顺序
     * 1、不断往左子树深入并不断入栈直到左叶子的空左孩子
     * 2、弹出栈顶，如果右孩子为null或者last是右孩子，则打印当前值；如果不是，则将指针指向右孩子
     * 3、循环1,2步骤直至栈为空且指针也为空
     */
    public void postOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        //上一次打印的结点
        Node last = null;
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                //出栈
                Node currentNode = stack.pop();
                if (currentNode.right == null || last == currentNode.right) {
                    //在这里面打印t并处理last之后，并不用处理root，因为之所以能进入这里，
                    // 是因为root一定等于null，所以下一轮循环一定还能进入这里，然后弹出t的父结点做处理
                    System.out.println(currentNode.e);
                    last = currentNode;
                } else {
                    //右孩子还没有打印过
                    stack.push(currentNode);
                    //因为当前结点未打印，所以要重新放回去，等右孩子打印完之后回来打印
                    node = currentNode.right;
                }
            }
        }


    }

    /**
     * 二叉查找树的层序遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            System.out.println(currentNode.e);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    /**
     * 获取二叉查找树中的最小值（递归）
     *
     * @return
     */
    public E getMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BinarySearchTree is empty.");
        }
        return getMin(root).e;
    }

    /**
     * 获取二叉查找树中的最小元素（递归）
     *
     * @return
     */
    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * 获取二叉查找树中的最小值(非递归)
     *
     * @return
     */
    public E getMinNR() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BinarySearchTree is empty.");
        }
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.e;
    }

    /**
     * 获取二叉查找树中的最大值（递归）
     *
     * @return
     */
    public E getMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BinarySearchTree is empty.");
        }
        return getMax(root).e;
    }

    /**
     * 获取二叉查找树中的最大元素（递归）
     *
     * @return
     */
    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    /**
     * 获取二叉查找树中的最大值(非递归)
     *
     * @return
     */
    public E getMaxNR() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BinarySearchTree is empty.");
        }
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.e;
    }

    /**
     * 从二叉查找树中删除最小的节点，返回最小值(递归)
     *
     * @return
     */
    public E removeMin() {
        E minElement = getMin();
        root = removeMin(root);
        return minElement;
    }

    /**
     * 删除掉以node为根的二叉查找树的最小节点，并返回删除后新的二叉查找树的根（递归）
     * 把删除节点的右节点移动到之前删除节点的位置
     *
     * @param node
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二叉查找树中删除最小的节点，返回最小值（非递归）
     *
     * @return
     */
    public E removeMinNR() {
        Node currentNode = root;
        Node parentNode = root;
        if (currentNode.left == null) {
            currentNode.left = currentNode.right;
            size--;
            return currentNode.e;
        }
        while (currentNode.left != null) {
            parentNode = currentNode;
            currentNode = currentNode.left;
        }
        parentNode.left = currentNode.right;
        currentNode.right = null;
        size--;
        return currentNode.e;
    }

    /**
     * 从二叉查找树中删除最大的节点，返回最大值（递归）
     *
     * @return
     */
    public E removeMax() {
        E maxElement = getMax();
        root = removeMax(root);
        return maxElement;
    }

    /**
     * 删除掉以node为根的二叉查找树的最大节点，并返回删除后新的二叉查找树的根（递归）
     * 把删除节点的左节点移动到之前删除节点的位置
     *
     * @param node
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 从二叉查找树中删除最大的节点，返回最小值（非递归）
     *
     * @return
     */
    public E removeMaxNR() {
        Node currentNode = root;
        Node parentNode = root;
        if (currentNode.right == null) {
            currentNode.right = currentNode.left;
            size--;
            return currentNode.e;
        }
        while (currentNode.right != null) {
            parentNode = currentNode;
            currentNode = currentNode.right;
        }
        parentNode.right = currentNode.left;
        currentNode.left = null;
        size--;
        return currentNode.e;
    }

    /**
     * 删除二叉查找树中元素为e的节点（递归）
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除二叉查找树中元素为e的节点（递归）
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if(e == null) {
            throw new IllegalArgumentException("e is null");
        }
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            //e == node.e
            //待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点(中继节点)
            // 用这个节点顶替待删除节点的位置
            //待删除节点右子树的最小节点
            Node successor = getMin(node.right);
            //把这个最小节点从待删除节点右子树进行删除，并把待删除节点的右子树赋给中继节点（removeMin有size--操作）
            successor.right = removeMin(node.right);
            //把待删除节点的左子树赋给中继节点
            successor.left = node.left;

//            // 待删除节点左右子树均不为空的情况
//            // 找到比待删除节点大的最大节点, 即待删除节点右子树的最大节点(前驱节点)
//            // 用这个节点顶替待删除节点的位置
//            //待删除节点左子树的最大节点
//            Node predecessor = getMax(node.left);
//            //把这个最小节点从待删除节点左子树进行删除，并把待删除节点的左子树赋给前驱节点（removeMax有size--操作）
//            predecessor.left = removeMax(node.left);
//            //把待删除节点的右子树赋给中继节点
//            predecessor.right = node.right;

            node.left = node.right = null;

            return successor;
        }
    }

    //    @Override
//    public String toString() {
//        return "BinarySearchTree{" +
//                "root=" + root +
//                ", size=" + size +
//                '}';
//    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            binarySearchTree.addNR(random.nextInt(10));
        }
        binarySearchTree.addNR(5);
        System.out.println(binarySearchTree);
        binarySearchTree.addNR(5);
        System.out.println(binarySearchTree);
        System.out.println("==========preOrder递归=============");
        binarySearchTree.preOrder();
        System.out.println("==========preOrder非递归=============");
        binarySearchTree.preOrderNR();
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("===========inOrder非递归============");
        binarySearchTree.inOrderNR();
        System.out.println("==========postOrder递归=============");
        binarySearchTree.postOrder();
        System.out.println("==========postOrder非递归=============");
        binarySearchTree.postOrderNR();
        System.out.println("==========levelOrder=============");
        binarySearchTree.levelOrder();
        System.out.println("==========getMin=============");
        System.out.println(binarySearchTree.getMin());
        System.out.println("==========getMinNR=============");
        System.out.println(binarySearchTree.getMinNR());
        System.out.println("==========getMax=============");
        System.out.println(binarySearchTree.getMax());
        System.out.println("==========getMaxNR=============");
        System.out.println(binarySearchTree.getMaxNR());
        System.out.println("==========removeMin=============");
        System.out.println(binarySearchTree.removeMin());
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("==========removeMax=============");
        System.out.println(binarySearchTree.removeMax());
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("==========removeMinNR=============");
        System.out.println(binarySearchTree.removeMinNR());
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("==========removeMaxNR=============");
        System.out.println(binarySearchTree.removeMaxNR());
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("===========remove============");
        binarySearchTree.remove(binarySearchTree.root, binarySearchTree.getMax());
        System.out.println("===========inOrder递归============");
        binarySearchTree.inOrder();
        System.out.println("=======================");
    }
}
