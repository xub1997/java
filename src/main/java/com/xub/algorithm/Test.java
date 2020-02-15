package com.xub.algorithm;

/**
 * @author xub
 * @Name: TestArray_Loop_LinkedList_Queue
 * @Description: TODO
 * @date 2019/12/25  20:30
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(reverseString("b  a  c".toCharArray()));
    }

    public static String reverseString(char[] s) {
        int end = s.length - 1;
        int begin = 0;
        for (; end > begin; end--, begin++) {
            char temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
        }
        return new String(s);
    }

    public static String reverse(String s) {
        if (s == null || s.split(" ").length == 0) {
            return null;
        }
        int begin = 0;
        int end = s.length() - 1;
        char[] ch = s.toCharArray();
        for (; begin < end; begin++, end--) {
            swap(ch, begin, end);
        }
        return new String(ch);
    }

    public static void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
