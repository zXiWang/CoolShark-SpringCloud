package day06;

import java.util.Arrays;

//求数组元素的最大值，并将最大值放在数组最后一个元素的下一个位置
public class MaxOfArray {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) { //遍历arr数组
            arr[i] = (int) (Math.random() * 100); //给每个元素赋值为0到99的之间的随机数
            System.out.println(arr[i]); //输出每个元素的值
        }

        int max = arr[0]; //假设第1个元素为最大值
        for (int i = 1; i < arr.length; i++) { //遍历剩余元素
            if (arr[i] > max) { //若剩余元素大于max
                max = arr[i]; //修改max为较大的
            }
        }
        System.out.println("最大值为:" + max);

        arr = Arrays.copyOf(arr, arr.length + 1); //扩容
        arr[arr.length - 1] = max; //将max赋值给arr的最后一个元素
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}


















