# 面向对象第三天扩展练习：

## 列表：

1. 创建小敌机数组、大敌机数组、小蜜蜂数组、子弹数组，并填充数据，遍历数组输出坐标并调用step()方法测试
2. 设计FlyingObject超类，设计6个类继承超类
3. 在FlyingObject中设计两个构造方法，一个是为三种敌人提供的，一个是为英雄机、天空、子弹提供的，6个类分别调用超类的构造方法

## 参考：

1. 创建小敌机数组、大敌机数组、小蜜蜂数组、子弹数组，并填充数据，遍历数组输出坐标并调用step()方法测试

   参考代码：

   ```java
   package cn.tedu.shoot;
   /** 整个游戏窗口 */
   public class World {
       public static void main(String[] args) {
           Airplane[] as = new Airplane[3];
           as[0] = new Airplane();
           as[1] = new Airplane();
           as[2] = new Airplane();
           for(int i=0;i<as.length;i++){
               System.out.println(as[i].x+","+as[i].y);
               as[i].step();
           }
           
           BigAirplane[] bas = new BigAirplane[3];
           bas[0] = new BigAirplane();
           bas[1] = new BigAirplane();
           bas[2] = new BigAirplane();
           for(int i=0;i<bas.length;i++){
               System.out.println(bas[i].x+","+bas[i].y);
               bas[i].step();
           }
           
           Bee[] bs = new Bee[];
           bs[0] = new Bee();
           bs[1] = new Bee();
           for(int i=0;i<bs.length;i++){
               System.out.println(bs[i].x+","+bs[i].y);
               bs[i].step();
           }
   
           Bullet[] bts = new Bullet[2];
           bts[0] = new Bullet(100,200);
           bts[1] = new Bullet(30,140);
           for(int i=0;i<bts.length;i++){
               System.out.println(bts[i].x+","+bts[i].y);
               bts[i].step();
           }
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 设计FlyingObject超类，设计6个类继承超类

   参考代码：

   ```java
   package cn.tedu.shoot;
   import java.util.Random;
   /** 飞行物 */
   public class FlyingObject {
       int width;  //宽
       int height; //高
       int x;      //x坐标
       int y;      //y坐标
   	
       /** 飞行物移动 */
       void step(){   //step()的处理-----------------明天下午讲
           System.out.println("飞行物移动啦!");
       }
   }
   
   package cn.tedu.shoot;
   /** 英雄机:是飞行物 */
   public class Hero extends FlyingObject {
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
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小敌机:是飞行物 */
   public class Airplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       Airplane(){
           width = 48;
           height = 50;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width); //x:0到(窗口宽-敌人宽)之内的随机数
           y = -height; //y:负的敌人的高
           speed = 2;
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 大敌机:是飞行物 */
   public class BigAirplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       BigAirplane(){
           width = 66;
           height = 89;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width); //x:0到(窗口宽-敌人宽)之内的随机数
           y = -height; //y:负的敌人的高
           speed = 2;
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小蜜蜂:是飞行物 */
   public class Bee extends FlyingObject {
       int xSpeed; //x坐标移动速度
       int ySpeed; //y坐标移动速度
       int awardType; //奖励类型
       /** 构造方法 */
       Bee(){
           width = 60;
           height = 51;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width); //x:0到(窗口宽-敌人宽)之内的随机数
           y = -height; //y:负的敌人的高
           xSpeed = 1;
           ySpeed = 2;
           awardType = rand.nextInt(2); //0到1之间随机生成
       }
   }
   
   package cn.tedu.shoot;
   /** 子弹:是飞行物 */
   public class Bullet extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */    //Bullet b = new Bullet(100,200);
       Bullet(int x,int y){ //子弹可以有多个，子弹的初始坐标要依赖于当前英雄机的坐标位置
           width = 8;
           height = 20;
           this.x = x;
           this.y = y;
           speed = 3;
       }
   }
   
   package cn.tedu.shoot;
   /** 天空:是飞行物 */
   public class Sky extends FlyingObject {
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
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 在FlyingObject中设计两个构造方法，一个是为三种敌人提供的，一个是为英雄机、天空、子弹提供的，6个类分别调用超类的构造方法

   参考代码：

   ```java
   package cn.tedu.shoot;
   import java.util.Random;
   /** 飞行物 */
   public class FlyingObject {
       int width;  //宽
       int height; //高
       int x;      //x坐标
       int y;      //y坐标
   
       /** 专门给小敌机、大敌机、小蜜蜂提供的 */
       //因为小敌机、大敌机、小蜜蜂的宽和高是不同的，意味着数据不能写死，所以传参
       //因为小敌机、大敌机、小蜜蜂的x和y是相同的，意味着数据可以写死
       FlyingObject(int width,int height){
           this.width = width;
           this.height = height;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(400-width); //x:0到(窗口宽-敌人宽)之内的随机数
           y = -height; //y:负的敌人的高
       }
   
       /** 专门给英雄机、天空、子弹提供的 */
       //因为英雄机、天空、子弹的宽/高/x/y都是不同的，意味着数据都不能写死，所以传参
       FlyingObject(int width,int height,int x,int y){
           this.width = width;
           this.height = height;
           this.x = x;
           this.y = y;
       }
   
       /** 飞行物移动 */
       void step(){
           System.out.println("飞行物移动啦!");
       }
   }
   
   package cn.tedu.shoot;
   /** 小敌机:是飞行物 */
   public class Airplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       Airplane(){ //Airplane a = new Airplane();
           super(48,50);
           speed = 2;
       }
   }
   
   package cn.tedu.shoot;
   /** 大敌机:是飞行物 */
   public class BigAirplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       BigAirplane(){
           super(66,89);
           speed = 2;
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小蜜蜂:是飞行物 */
   public class Bee extends FlyingObject {
       int xSpeed; //x坐标移动速度
       int ySpeed; //y坐标移动速度
       int awardType; //奖励类型
       /** 构造方法 */
       Bee(){
           super(60,51);
           xSpeed = 1;
           ySpeed = 2;
           Random rand = new Random(); //随机数对象
           awardType = rand.nextInt(2); //0到1之间随机生成
       }
   }
   
   package cn.tedu.shoot;
   /** 英雄机:是飞行物 */
   public class Hero extends FlyingObject {
       int life;   //命
       int fire;   //火力值
       /** 构造方法 */
       Hero(){
           super(97,139,140,400);
           life = 3;
           fire = 0;
       }
   }
   
   package cn.tedu.shoot;
   /** 天空:是飞行物 */
   public class Sky extends FlyingObject {
       int speed; //移动速度
       int y1; //第2张图片的y坐标
       /** 构造方法 */
       Sky(){
           super(400,700,0,0);
           speed = 1;
           y1 = -700;
       }
   }
   
   package cn.tedu.shoot;
   /** 子弹:是飞行物 */
   public class Bullet extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */    //Bullet b = new Bullet(100,200);
       Bullet(int x,int y){ //子弹可以有多个，子弹的初始坐标要依赖于当前英雄机的坐标位置
           super(8,20,x,y);
           speed = 3;
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

   
