package com.xub.java.sort;

import java.util.Arrays;
import java.util.Random;

public class  TestSort {
    public static void main(String args[]) {
        //测试元素（根据自己的需要修改个数、范围）
        int[] numbers = new int[100];
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            //生成1-1000之间的随机数
            numbers[i] = random.nextInt(1000) + 1;
        }
        System.out.println(String.format("原数组： %s", Arrays.toString(numbers)));
        //结果

        //运算结果消耗时间太小，换成纳秒
        //开始时间
        long start = System.nanoTime();
        int[] result1 = BubbleSort.Sort(numbers);
        //结束时间
        long end = System.nanoTime();
        //耗时
        long spend = end - start;
        System.out.println(String.format("冒泡第一种排序结果： %s \n耗时： %d 毫秒", Arrays.toString(result1), spend / 1000));
        start = System.nanoTime();
        int[] result2 = BubbleSort.BetterSort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("冒泡第二种排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result2), spend / 1000));
        start = System.nanoTime();
        int[] result3 = InsertSort.Sort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("插入排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result3), spend / 1000));
        start = System.nanoTime();
        int[] result4 = SelectSort.SimpleSort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("简单选择排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result4), spend / 1000));
        start = System.nanoTime();
        int[] result5 = ShellSort.Sort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("希尔排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result5), spend / 1000));
        start = System.nanoTime();
        int[] result6 = HeapSort.Sort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("堆排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result6), spend / 1000));
        start = System.nanoTime();
        int[] result7 = MergeSort.Sort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("归并排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result7), spend / 1000));
        start = System.nanoTime();
        int[] result8 = QuickSort.Sort(numbers);
        end = System.nanoTime();
        spend = end - start;
        System.out.println(String.format("快速排序结果：%s \n耗时： %d 毫秒", Arrays.toString(result8), spend / 1000));
    }
}
