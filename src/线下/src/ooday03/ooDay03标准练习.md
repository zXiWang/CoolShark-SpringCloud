# 面向对象第三天标准练习：

## 列表：

1. 创建侦察艇数组、鱼雷艇数组、水雷艇数组、水雷数组、深水炸弹数组，并填充数据，遍历数组输出坐标并调用move()方法测试
2. 创建SeaObject超类，设计6个类继承超类
3. 在SeaObject中设计两个构造方法，一个是为三种潜艇提供的，一个是为战舰、水雷、深水炸弹提供的，6个类分别调用超类的构造方法

## 参考：

1. 创建侦察艇数组、鱼雷艇数组、水雷艇数组、水雷数组、深水炸弹数组，并填充数据，遍历数组输出坐标并调用move()方法测试

   参考代码：

   ```java
   package day03;
   /** 整个游戏世界 */
   public class World {
       public static void main(String[] args) {
           ObserveSubmarine[] oses = new ObserveSubmarine[3];
           TorpedoSubmarine[] tses = new TorpedoSubmarine[2];
           MineSubmarine[] mses = new MineSubmarine[3]; //水雷潜艇数组
           mses[0] = new MineSubmarine();
           mses[1] = new MineSubmarine();
           mses[2] = new MineSubmarine();
           for(int i=0;i<mses.length;i++){ //遍历所有水雷潜艇
               System.out.println(mses[i].x+","+mses[i].y); //输出每个水雷潜艇的x和y坐标
               mses[i].move(); //每个水雷潜艇移动
           }
   
           Mine[] ms = new Mine[2]; //水雷数组
           ms[0] = new Mine(100,200);
           ms[1] = new Mine(200,400);
           for(int i=0;i<ms.length;i++){
               System.out.println(ms[i].x+","+ms[i].y);
               ms[i].move();
           }
           Bomb[] bs = new Bomb[2];
   
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 创建SeaObject超类，设计6个类继承超类

   参考代码：

   ```java
   package day03;
   import java.util.Random;
   //海洋对象
   public class SeaObject {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("海洋对象移动");
       }
   }
   
   package day03;
   //战舰
   public class Battleship extends SeaObject {
       int life;
       Battleship(){
           width = 66;
           height = 26;
           x = 270;
           y = 124;
           speed = 20;
           life = 5;
       }
   }
   
   package day03;
   import java.util.Random;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject {
       ObserveSubmarine(){
           width = 63;
           height = 19;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
   }
   
   package day03;
   import java.util.Random;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject {
       TorpedoSubmarine(){
           width = 64;
           height = 20;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
   }
   
   package day03;
   import java.util.Random;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       MineSubmarine(){
           width = 63;
           height = 19;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
   }
   
   package day03;
   //水雷
   public class Mine extends SeaObject {
       Mine(int x,int y){
           width = 11;
           height = 11;
           this.x = x;
           this.y = y;
           speed = 1;
       }
   }
   
   package day03;
   //深水炸弹
   public class Bomb extends SeaObject {
       Bomb(int x,int y){
           width = 9;
           height = 12;
           this.x = x;
           this.y = y;
           speed = 3;
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 在SeaObject中设计两个构造方法，一个是为三种潜艇提供的，一个是为战舰、水雷、深水炸弹提供的，6个类分别调用超类的构造方法

   参考代码：

   ```java
   package day03;
   import java.util.Random;
   //海洋对象
   public class SeaObject {
       int width;
       int height;
       int x;
       int y;
       int speed;
       /** 专门给三种潜艇提供的 */
       SeaObject(int width,int height){
           this.width = width;
           this.height = height;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       /** 专门给战舰、水雷、深水炸弹提供的 */
       SeaObject(int width,int height,int x,int y,int speed){
           this.width = width;
           this.height = height;
           this.x = x;
           this.y = y;
           this.speed = speed;
       }
   
       void move(){
           System.out.println("海洋对象移动");
       }
   }
   
   package day03;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject {
       ObserveSubmarine(){
           super(63,19);
       }
   }
   
   package day03;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject {
       TorpedoSubmarine(){
           super(64,20);
       }
   }
   
   package day03;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       MineSubmarine(){
           super(63,19);
       }
   }
   
   package day03;
   //战舰
   public class Battleship extends SeaObject {
       int life;
       Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   }
   
   package day03;
   //水雷
   public class Mine extends SeaObject {
       Mine(int x,int y){
           super(11,11,x,y,1);
       }
   }
   
   package day03;
   //深水炸弹
   public class Bomb extends SeaObject {
       Bomb(int x,int y){
           super(9,12,x,y,3);
       }
   }
   
   //注:其余类没有变化，此处省略
   ```
