package sort;

import java.util.Arrays;
import java.util.Random;

public class TestSort {
    public static void main(String args[]){
        //测试元素（根据自己的需要修改个数、范围）
        int[] numbers=new int[1000];
        Random random=new Random();

        for(int i=0;i<1000;i++){
            //生成1-100之间的随机数
            numbers[i]= random.nextInt(1000)+1;
        }
        System.out.println("原数组："+ Arrays.toString(numbers));
        //结果

        //运算结果消耗时间太小，换成纳秒
        //开始时间
        long start=System.nanoTime();
        int[] result1=BubbleSort.Sort(numbers);
        //结束时间
        long end=System.nanoTime();
        //耗时
        long spend=end-start;
        System.out.println("冒泡第一种排序结果："+Arrays.toString(result1)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result2=BubbleSort.BetterSort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("冒泡第二种排序结果："+Arrays.toString(result2)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result3=InsertSort.Sort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("插入排序结果："+Arrays.toString(result3)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result4=SelectSort.SimpleSort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("简单选择排序结果："+Arrays.toString(result4)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result5=ShellSort.Sort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("希尔排序结果："+Arrays.toString(result5)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result6=HeapSort.Sort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("堆排序结果："+Arrays.toString(result6)+"\n耗时："+spend/1000+"毫秒");
        start=System.nanoTime();
        int[] result7=MergeSort.Sort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("归并排序结果："+Arrays.toString(result7)+"\n耗时："+spend/1000+"毫秒");
        /*start=System.nanoTime();
        int[] result8=QuickSort.Sort(numbers);
        end=System.nanoTime();
        spend=end-start;
        System.out.println("快速排序结果："+Arrays.toString(result8)+"\n耗时："+spend/1000+"毫秒");*/
    }
}
