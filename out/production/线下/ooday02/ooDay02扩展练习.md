# 面向对象第二天扩展练习：

## 列表：

1. 给天空类、英雄机类、小敌机类、大敌机类、小蜜蜂类、子弹类添加构造方法
2. 在main中：创建1个天空对象、1个英雄机对象、至少4个小敌机对象、2个大敌机对象、2个小蜜蜂对象、2个子弹对象，测试



## 参考：

1. 给天空类、英雄机类、小敌机类、大敌机类、小蜜蜂类、子弹类添加构造方法

   参考代码：

   ```java
   package cn.tedu.shoot;
   /** 英雄机 */
   public class Hero {
       int width;  //宽
       int height; //高
       int x;      //x坐标
       int y;      //y坐标
       int life;   //命
       int fire;   //火力值
       /** 构造方法 */
       Hero(){
           width = 97;
           height = 139;
           x = 140;
           y = 400;
           life = 3;
           fire = 0;
       }
   
       /** 移动 */
       void step(){
           System.out.println("英雄机切换图片啦!");
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小敌机 */
   public class Airplane {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
       /** 构造方法 */
       Airplane(){
           width = 48;
           height = 50;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width+1); //x:0到(窗口宽-小敌机宽)之内的随机数
           y = -height; //y:负的小敌机的高
           speed = 2;
       }
   
       void step(){
           System.out.println("小敌机的y向下移动");
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 大敌机 */
   public class BigAirplane {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
       /** 构造方法 */
       BigAirplane(){
           width = 66;
           height = 89;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width+1); //x:0到(窗口宽-大敌机宽)之内的随机数
           y = -height; //y:负的大敌机的高
           speed = 2;
       }
   
       void step(){
           System.out.println("大敌机的y向下移动");
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小蜜蜂 */
   public class Bee {
       int width;
       int height;
       int x;
       int y;
       int xSpeed; //x坐标移动速度
       int ySpeed; //y坐标移动速度
       int awardType; //奖励类型
       /** 构造方法 */
       Bee(){
           width = 60;
           height = 51;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width+1); //x:0到(窗口宽-小蜜蜂宽)之内的随机数
           y = -height; //y:负的小蜜蜂的高
           xSpeed = 1;
           ySpeed = 2;
           awardType = rand.nextInt(2); //0到1之间随机生成
       }
   
       void step(){
           System.out.println("小蜜蜂的x左右移动、y向下移动");
       }
   }
   
   package cn.tedu.shoot;
   /** 子弹 */
   public class Bullet {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
       /** 构造方法 */
       Bullet(int x,int y){ //子弹可以有多个，子弹的初始坐标要依赖于当前英雄机的坐标位置
           width = 8;
           height = 20;
           this.x = x;
           this.y = y;
           speed = 3;
       }
   
       void step(){
           System.out.println("子弹的y向上移动");
       }
   }
   
   package cn.tedu.shoot;
   /** 天空 */
   public class Sky {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
       int y1; //第2张图片的y坐标
       /** 构造方法 */
       Sky(){
           width = 400;
           height = 700;
           x = 0;
           y = 0;
           speed = 1;
           y1 = -700;
       }
   
       void step(){
           System.out.println("天空的的y和y1同时向下移动");
       }
   }
   
   //注:其余类没有变化，此处省略
   ```
   
2. 在main中：创建1个天空对象、1个英雄机对象、至少4个小敌机对象、2个大敌机对象、2个小蜜蜂对象、2个子弹对象，测试

   参考代码：

   ```java
   package cn.tedu.shoot;
   /** 整个游戏窗口 */
   public class World {
       public static void main(String[] args) {
           Sky s = new Sky();
           Hero h = new Hero();
           Airplane a1 = new Airplane();
           Airplane a2 = new Airplane();
           Airplane a3 = new Airplane();
           Airplane a4 = new Airplane();
           BigAirplane ba1 = new BigAirplane();
           BigAirplane ba2 = new BigAirplane();
           Bee b1 = new Bee();
           Bee b2 = new Bee();
           Bullet bt1 = new Bullet(100,200);
           Bullet bt2 = new Bullet(50,300);
           System.out.println(h.width+","+h.height+","+h.x+","+h.y+","+h.life+","+h.fire);      System.out.println(a1.width+","+a1.height+","+a1.x+","+a1.y+","+a1.speed);    System.out.println(a2.width+","+a2.height+","+a2.x+","+a2.y+","+a2.speed);    System.out.println(a3.width+","+a3.height+","+a3.x+","+a3.y+","+a3.speed);    System.out.println(a4.width+","+a4.height+","+a4.x+","+a4.y+","+a4.speed);
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

   
