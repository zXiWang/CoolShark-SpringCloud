# 语言基础第五天扩展练习：

## 列表：

1. 利用for循环计算：求数字1到100之内，所有偶数的和，并输出

2. 利用for循环计算：求8的阶乘，并输出

3. 利用for循环计算：打印字符*组成的等腰三角形，输出结果如下所示：

   ```java
        *
       ***
      *****
     *******
    *********
   ***********
   ```

## 参考：

1. 利用for循环计算：求数字1到100之内，所有偶数的和，并输出

   参考代码：

   ```java
   package test;
   public class Day05 {
       public static void main(String[] args) {
   		int sum = 0;
           for(int i=2;i<=100;i+=2){
               sum+=i;
           }
           System.out.println("sum="+sum);
       }
   }
   ```

2. 利用for循环计算：求8的阶乘，并输出

   参考代码：

   ```java
   package test;
   public class Day05 {
       public static void main(String[] args) {
   		int num = 1; //阶乘
           for(int i=1;i<=8;i++){
               num*=i;
           }
           System.out.println("num="+num);
       }
   }
   ```

3. 利用for循环计算：打印字符*组成的等腰三角形，输出结果如下所示：

   ```java
        *
       ***
      *****
     *******
    *********
   ***********
   ```

   参考代码：

   ```java
   package test;
   public class Day05 {
       public static void main(String[] args) {
   		for(int i=1;i<=6;i++){
               for(int j=1;j<=6-i;j++){ //空格
                   System.out.print(" ");
               }
               for(int k=1;k<=2*i-1;k++){
                   System.out.print("*");
               }
               System.out.println();
           }
       }
   }
   ```

