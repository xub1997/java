package com.xub.java.data_structure.collection;

/**
 * @author xub
 * @Name: BinarySearchTreeMap
 * @Description: TODO
 * @date 2020/1/14  22:45
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        private K k;
        private V v;
        public Node left;
        public Node right;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + k +
                    ",value=" + v +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;
    private int size;

    public BinarySearchTreeMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K k, V v) {
        root = add(root, k, v);
    }

    private Node add(Node node, K k, V v) {
        if (node == null) {
            size++;
            return new Node(k, v);
        }
        if (k.compareTo(node.k) < 0) {
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.k) > 0) {
            node.right = add(node.right, k, v);
        } else {
            node.v = v;
        }
        return node;
    }

    @Override
    public V remove(K k) {
        if (contains(k)) {
            Node node = remove(root, k);
            return node.v;
        }
        return null;
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

    private Node remove(Node node, K k) {
        if (k == null) {
            throw new IllegalArgumentException("k is null");
        }
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.k) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.k) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {
            //k == node.k
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


            node.left = node.right = null;

            return successor;
        }
    }

    @Override
    public boolean contains(K k) {
        return contains(root, k);
    }

    private boolean contains(Node node, K k) {
        if (node == null) {
            return false;
        }
        if (k.compareTo(node.k) == 0) {
            return true;
        } else if (k.compareTo(node.k) < 0) {
            return contains(node.left, k);
        } else {
            return contains(node.right, k);
        }
    }

    @Override
    public V get(K k) {
        Node node = getNode(root, k);
        return node == null ? null : node.v;
    }

    private Node getNode(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.k) == 0) {
            return node;
        } else if (k.compareTo(node.k) < 0) {
            return getNode(node.left, k);
        } else {
            return getNode(node.right, k);
        }
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(root, k);
        if (node == null) {
            throw new IllegalArgumentException(String.format("%s doesn't exist!", k));
        } else {
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

    public static void main(String[] args) {

    }
}
