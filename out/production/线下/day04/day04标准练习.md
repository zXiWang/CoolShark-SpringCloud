# 语言基础第四天标准练习：

## 列表：

1. ScoreLevel成绩等级判断：

   要求：接收用户输入的成绩，判断成绩的等级ABCD

2. CommandBySwitch命令解析程序：

   要求：接收用户输入的命令，依据命令做不同的操作

3. Guessing猜数字之while版：

   要求：随机生成一个数，由用户来猜，猜不对则反复猜，并给出大小提示，猜对的则结束，用while来实现。

4. Guessing猜数字之do...while版

   要求：随机生成一个数，由用户来猜，猜不对则反复猜，并给出大小提示，猜对的则结束，用do...while来实现。



## 参考：

1. ScoreLevel成绩等级判断：接收用户输入的成绩，判断成绩的等级ABCD

   参考代码：

   ```java
   package day04;
   import java.util.Scanner;
   //成绩等级判断
   public class ScoreLevel {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           System.out.println("请输入成绩:");
           double score = scan.nextDouble();
   
           //带数(888,-45,95,85,65,45)
           if(score<0 || score>100){
               System.out.println("成绩不合法");
           }else if(score>=90){ //合法
               System.out.println("A-优秀");
           }else if(score>=80){
               System.out.println("B-良好");
           }else if(score>=60){
               System.out.println("C-中等");
           }else{
               System.out.println("D-不及格");
           }
   
       }
   }
   ```

2. CommandBySwitch命令解析程序：接收用户输入的命令，依据命令做不同的操作

   参考代码：

   ```java
   package day04;
   import java.util.Scanner;
   //命令解析程序
   public class CommandBySwitch {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           System.out.println("请选择功能: 1.取款  2.存款  3.查询余额  0.退卡");
           int command = scan.nextInt();
   
           switch(command){
               case 1:
                   System.out.println("取款操作...");
                   break;
               case 2:
                   System.out.println("存款操作...");
                   break;
               case 3:
                   System.out.println("查询余额操作...");
                   break;
               case 0:
                   System.out.println("退出成功");
                   break;
               default:
                   System.out.println("输入错误");
           }
       }
   }
   ```

3. Guessing猜数字之while版：随机生成一个数，由用户来猜，猜不对则反复猜，并给出大小提示，猜对的则结束，用while来实现。

   参考代码：

   ```java
   package day04;
   import java.util.Scanner;
   //猜数字小游戏
   public class Guessing {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int num = (int)(Math.random()*1000+1); //1到1000之内
           System.out.println(num); //作弊
           
           //300(大),200(小),250(对)
           System.out.println("猜吧!");
           int guess = scan.nextInt(); //1.
           while(guess!=num){ //2.
               if(guess>num){
                   System.out.println("太大了");
               }else{
                   System.out.println("太小了");
               }
               System.out.println("猜吧!");
               guess = scan.nextInt(); //3.
           }
           System.out.println("恭喜你猜对了!");
       }
   }
   ```

4. Guessing猜数字之do...while版：随机生成一个数，由用户来猜，猜不对则反复猜，并给出大小提示，猜对的则结束，用do...while来实现。

   参考代码:

   ```java
   package day04;
   import java.util.Scanner;
   //猜数字小游戏
   public class Guessing {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int num = (int)(Math.random()*1000+1); //1到1000之内
           System.out.println(num); //作弊
   
           //假设num=250
           //300(大),200(小),250(对)
           int guess;
           do{
               System.out.println("猜吧!");
               guess = scan.nextInt(); //1+3
               if(guess>num){
                   System.out.println("太大了");
               }else if(guess<num){
                   System.out.println("太小了");
               }else{
                   System.out.println("恭喜你猜对了");
               }
           }while(guess!=num); //2
       }
   }
   ```

