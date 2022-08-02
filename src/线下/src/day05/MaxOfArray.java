package day05;

public class MaxOfArray {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) { //遍历arr数组
            arr[i] = (int) (Math.random() * 100); //给每个元素赋值为0到99的之间的随机数
            System.out.println(arr[i]); //输出每个元素的值
        }
    }
}











