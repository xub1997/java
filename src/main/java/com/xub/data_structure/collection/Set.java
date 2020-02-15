package com.xub.data_structure.collection;

public interface Set<E> {

    /**
     *
     * @param e
     */
    void add(E e);

    /**
     *
     * @param e
     */
    void remove(E e);

    /**
     *
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     *
     * @return
     */
    int getSize();


    /**
     *
     * @return
     */
    boolean isEmpty();
}
