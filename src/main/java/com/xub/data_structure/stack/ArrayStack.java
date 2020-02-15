package com.xub.data_structure.stack;

import com.xub.java.data_structure.arrays.Array;

/**
 * @author xub
 * @Name: ArrayStack
 * @Description: TODO
 * @date 2020/1/7  14:36
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack() {
        this.array = new Array<>();
    }

    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    /**
     * 进栈
     *
     * @param element
     */
    @Override
    public void push(E element) {
        array.addLast(element);
    }

    /**
     * 出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 获取栈中元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 获取数组栈的容量
     *
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 获取栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("top [");
        int size = array.getSize();
        for (int i = size - 1; i >= 0; i--) {
            res.append(array.get(i));
            if (i != 0) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }
        for (int i = 0; i < 10; i++) {
            arrayStack.pop();
            System.out.println(arrayStack);
        }
        System.out.println(arrayStack.isValid("(){}[]"));
        System.out.println(arrayStack.isValid("({[]})"));
    }

    //常见问题：括号匹配
    public boolean isValid(String s) {
        //使用jdk原本的java.util.Stack
        //java.util.Stack<Character> stack = new java.util.Stack<>();
        //使用自定义的ArrayStack
        ArrayStack<Character> stack = new ArrayStack();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == '{' && topChar != '}') {
                    return false;
                }
                if (c == '[' && topChar != ']') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
