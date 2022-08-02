# 面向对象第二天标准练习：

## 列表：

1. 给战舰类、侦察潜艇类、鱼雷潜艇类、水雷潜艇类、水雷类、深水炸弹类添加构造方法
2. 在main中：创建1个战舰对象、至少4个侦察潜艇对象、2个鱼雷潜艇对象、2个水雷潜艇对象、2个水雷对象、2个深水炸弹对象，测试



## 参考：

1. 给战舰类、侦察潜艇类、鱼雷潜艇类、水雷潜艇类、水雷类、深水炸弹类，添加构造方法

   参考代码：

   ```java
   package day02;
   //战舰
   public class Battleship {
       int width;
       int height;
       int x;
       int y;
       int speed;
       int life;
       Battleship(){
           width = 66;
           height = 26;
           x = 270;
           y = 124;
           speed = 2;
           life = 5;
       }
   
       void move(){
           System.out.println("战舰移动");
       }
   }
   
   package day02;
   import java.util.Random;
   //侦察潜艇
   public class ObserveSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       ObserveSubmarine(){
           width = 63;
           height = 19;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       void move(){
           System.out.println("侦察潜艇x向右移动");
       }
   }
   
   package day02;
   import java.util.Random;
   //鱼雷潜艇
   public class TorpedoSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       TorpedoSubmarine(){
           width = 64;
           height = 20;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       void move(){
           System.out.println("鱼雷潜艇x向右移动");
       }
   }
   
   package day02;
   import java.util.Random;
   //水雷潜艇
   public class MineSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       MineSubmarine(){
           width = 63;
           height = 19;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(479-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       void move(){
           System.out.println("水雷潜艇x向右移动");
       }
   }
   
   package day02;
   //水雷
   public class Mine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       Mine(int x,int y){
           width = 11;
           height = 11;
           this.x = x;
           this.y = y;
           speed = 1;
       }
       void move(){
           System.out.println("水雷y向上移动");
       }
   }
   
   package day02;
   //深水炸弹
   public class Bomb {
       int width;
       int height;
       int x;
       int y;
       int speed;
       Bomb(int x,int y){
           width = 9;
           height = 12;
           this.x = x;
           this.y = y;
           speed = 3;
       }
   
       void move(){
           System.out.println("深水炸弹y向下移动");
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 在main中：创建1个战舰对象、至少4个侦察潜艇对象、2个鱼雷潜艇对象、2个水雷潜艇对象、2个水雷对象、2个深水炸弹对象，测试

   ```java
   package day02;
   /** 整个游戏世界 */
   public class World {
       public static void main(String[] args) {
           Battleship s = new Battleship();
           ObserveSubmarine os1 = new ObserveSubmarine();
           ObserveSubmarine os2 = new ObserveSubmarine();
           ObserveSubmarine os3 = new ObserveSubmarine();
           ObserveSubmarine os4 = new ObserveSubmarine();
           TorpedoSubmarine ts1 = new TorpedoSubmarine();
           TorpedoSubmarine ts2 = new TorpedoSubmarine();
           MineSubmarine ms1 = new MineSubmarine();
           MineSubmarine ms2 = new MineSubmarine();
           Mine m1 = new Mine(123,345);
           Mine m2 = new Mine(345,234);
           Bomb b1 = new Bomb(200,300);
           Bomb b2 = new Bomb(300,400);
           //最少输出4个对象的数据(一定要包括2个侦察潜艇的数据)
           System.out.println(s.width+","+s.height+","+s.x+","+s.y+","+s.speed+","+s.life);
    System.out.println(os1.width+","+os1.height+","+os1.x+","+os1.y+","+os1.speed);
    System.out.println(os2.width+","+os2.height+","+os2.x+","+os2.y+","+os2.speed);
    System.out.println(os3.width+","+os3.height+","+os3.x+","+os3.y+","+os3.speed);
    System.out.println(os4.width+","+os4.height+","+os4.x+","+os4.y+","+os4.speed);
   
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

   







