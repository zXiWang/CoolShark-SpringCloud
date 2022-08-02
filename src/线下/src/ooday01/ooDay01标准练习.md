# 面向对象第一天标准练习：

## 列表

1. 创建战舰类、侦察潜艇类、鱼雷潜艇类、水雷潜艇类、水雷类、深水炸弹类，设计类中的成员变量和move()方法
2. 创建World类，在main中：创建1个战舰对象、至少2个侦察潜艇对象、2个鱼雷潜艇对象、2个水雷潜艇对象、2个水雷对象、2个深水炸弹对象，并给属性赋值，调用方法测试

## 参考

1. 创建战舰类、侦察潜艇类、鱼雷潜艇类、水雷潜艇类、水雷类、深水炸弹类，设计类中的成员变量和move()方法

   参考代码：

   ```java
   package day01;
   //战舰
   public class Battleship {
       int width;
       int height;
       int x;
       int y;
       int speed;
       int life;
   
       void move(){
           System.out.println("战舰移动");
       }
   }
   
   ```

   ```java
   package day01;
   //侦察潜艇
   public class ObserveSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("侦察潜艇x向右移动");
       }
   }
   ```
   ```java
   package day01;
   //鱼雷潜艇
   public class TorpedoSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("鱼雷潜艇x向右移动");
       }
   }
   ```
   ```java
   package day01;
   //水雷潜艇
   public class MineSubmarine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("水雷潜艇x向右移动");
       }
   }
   ```
   ```java
   package day01;
   //水雷
   public class Mine {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("水雷y向上移动");
       }
   }
   ```
   ```java
   package day01;
   //深水炸弹
   public class Bomb {
       int width;
       int height;
       int x;
       int y;
       int speed;
       void move(){
           System.out.println("深水炸弹y向下移动");
       }
   }
   ```

2. 创建World类，在main中：创建1个战舰对象、至少2个侦察潜艇对象、2个鱼雷潜艇对象、2个水雷潜艇对象、2个水雷对象、2个深水炸弹对象，并给属性赋值，调用方法测试

   参考代码：

   ```java
   package day01;
   /** 整个游戏世界 */
   public class World {
       public static void main(String[] args) {
           Battleship s = new Battleship();
           s.width = 50;
           s.height = 30;
           s.x = 100;
           s.y = 200;
           s.speed = 20;
           s.life = 5;
           System.out.println(s.width+","+s.height+","+s.x+","+s.y+","+s.speed+","+s.life);
           s.move();
   
           ObserveSubmarine os1 = new ObserveSubmarine();
           os1.width = 30;
           os1.height = 40;
           os1.x = 200;
           os1.y = 400;
           os1.speed = 3;
           System.out.println(os1.width+","+os1.height+","+os1.x+","+os1.y+","+os1.speed);
           os1.move();
   
           ObserveSubmarine os2 = new ObserveSubmarine();
           os2.width = 30;
           os2.height = 40;
           os2.x = 100;
           os2.y = 450;
           os2.speed = 3;
           System.out.println(os2.width+","+os2.height+","+os2.x+","+os2.y+","+os2.speed);
           os2.move();
   
           TorpedoSubmarine ts1 = new TorpedoSubmarine();
           TorpedoSubmarine ts2 = new TorpedoSubmarine();
           MineSubmarine ms1 = new MineSubmarine();
           MineSubmarine ms2 = new MineSubmarine();
           Mine m1 = new Mine();
           Mine m2 = new Mine();
           Bomb b1 = new Bomb();
           Bomb b2 = new Bomb();
       }
   }
   ```
   
   
