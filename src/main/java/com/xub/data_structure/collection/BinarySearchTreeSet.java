package com.xub.data_structure.collection;

import com.xub.java.data_structure.binary_search_tree.BinarySearchTree;

/**
 * @author xub
 * @Name: BinarySearchTreeSet
 * @Description: TODO
 * @date 2020/1/14  15:53
 */
public class BinarySearchTreeSet<E extends Comparable<E>>  implements Set<E>{

    private BinarySearchTree<E> binarySearchTree;

    public BinarySearchTreeSet() {
        this.binarySearchTree = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        binarySearchTree.add(e);
    }

    @Override
    public void remove(E e) {
        binarySearchTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return binarySearchTree.contains(e);
    }

    @Override
    public int getSize() {
        return binarySearchTree.size();
    }

    @Override
    public boolean isEmpty() {
        return binarySearchTree.isEmpty();
    }

    public static void main(String[] args){

    }
}
