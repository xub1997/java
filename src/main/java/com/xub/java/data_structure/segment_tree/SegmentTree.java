package com.xub.java.data_structure.segment_tree;

/**
 * @author xub
 * @Name: SegmentTree
 * @Description: TODO
 * @date 2020/3/8  15:08
 */
public class SegmentTree<E> {

    private E[] tree;

    private E[] datas;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        datas = (E[]) new Object[arr.length];
        for (int i = 0, length = arr.length; i < length; i++) {
            datas[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, datas.length - 1);
    }

    //在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = datas[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return datas.length;
    }

    public E get(int index) {
        if (index < 0 || index >= datas.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return datas[index];
    }

    //返回完全二叉树的数组表示中，一个索引所表示的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= datas.length ||
                queryR < 0 || queryR >= datas.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, datas.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e){

        if(index < 0 || index >= datas.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        datas[index] = e;
        set(0, 0, datas.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){

        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        else { // index <= mid
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                stringBuilder.append(tree[i]);
            } else {
                stringBuilder.append("null");
            }
            if (i != tree.length - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (Integer a, Integer b) -> (a + b));
        System.out.println(segmentTree);
    }


}
