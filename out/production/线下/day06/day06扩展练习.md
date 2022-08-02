# 语言基础第六天扩展练习：

## 列表：

1. 定义一个方法generate()

   - 根据第一个参数生成指定长度的int数组；
   - 根据第二个范围参数随机生成0到指定范围的随机数，填充到数组元素中，并将该数组返回；
   - 测试数组内容。



## 参考：

1. 定义一个方法generate()

   - 根据第一个参数生成指定长度的int数组；
   - 根据第二个范围参数随机生成0到指定范围的随机数，填充到数组元素中，并将该数组返回；
   - 测试数组内容。

   参考代码：

   ```java
   package test;
   public class Day06 {
       public static void main(String[] args) {
   		int[] arr = generate(5,20);
           for(int i=0;i<arr.length;i++){
               System.out.println(arr[i]);
           }
       }
       //生成随机数组方法
       public static int[] generate(int len,int max){
           int[] arr = new int[len];
           for(int i=0;i<arr.length;i++){
               arr[i] = (int)(Math.random()*(max+1));
           }
           return arr;
       }
   }
   ```

