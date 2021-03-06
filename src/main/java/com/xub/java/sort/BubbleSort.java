package com.xub.java.sort;


public class BubbleSort {
    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * 时间复杂度：O（n^2） 稳定排序
     *
     * @param numbers 需要排序的整型数组
     */
    public static int[] BetterSort(int[] numbers) {
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (numbers[i] > numbers[j]) {
                    swap(numbers, i, j);
                }
            }
        }

        return numbers;
    }

    /*
     * 改进版
     * @param numbers 需要排序的整型数组
     * */
    public static int[] Sort(int[] numbers) {
        int length = numbers.length;
        for (int i = 0; i < length - 1; i++) {
            //前面的已经比较过，可以忽略（减去i个数）
            for (int j = 0; j < length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1])  //交换两数位置
                {
                    swap(numbers, i, j);
                }
            }
        }
        return numbers;
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[j];
        numbers[j] = numbers[j + 1];
        numbers[j + 1] = temp;
    }


}
