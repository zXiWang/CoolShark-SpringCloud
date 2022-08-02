# 面向对象第一天扩展练习：

## 飞机大战需求：

1. 所参与的角色：英雄机、子弹、小敌机、大敌机、小蜜蜂、天空

2. 角色间的关系：

   - 英雄机发射子弹(单倍火力、双倍火力)

   - 子弹打敌人(小敌机、大敌机、小蜜蜂)，若击中了：

     - 子弹直接消失、敌人先爆破再消失

     - 若击中的是小敌机，则玩家得1分

       若击中的是大敌机，则玩家得3分

       若击中的是小蜜蜂，则英雄机得奖励(1条命，或，40火力值)

   - 敌人(小敌机、大敌机、小蜜蜂)撞英雄机，若撞上了：

     - 敌人先爆破再消失
     - 英雄机减1条命，同时清空火力值----当命数为0时，游戏结束

   - 英雄机、子弹、小敌机、大敌机、小蜜蜂，都在天空中飞



## 列表：

1. 创建英雄机类、小敌机类、大敌机类、小蜜蜂类、子弹类、天空类，设计类中的成员变量和step()方法
2. 创建World类，在main中：创建1个天空对象、1个英雄机对象、至少2个小敌机对象、2个大敌机对象、2个小蜜蜂对象、2个子弹对象，并给属性赋值，调用方法测试



## 参考：

1. 创建英雄机类、小敌机类、大敌机类、小蜜蜂类、子弹类、天空类，设计类中的成员变量和step()方法

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
   
       /** 移动 */
       void step(){
           System.out.println("英雄机切换图片啦!");
       }
   }
   
   package cn.tedu.shoot;
   /** 小敌机 */
   public class Airplane {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
   
       void step(){
           System.out.println("小敌机的y向下移动");
       }
   }
   
   package cn.tedu.shoot;
   /** 大敌机 */
   public class BigAirplane {
       int width;
       int height;
       int x;
       int y;
       int speed; //移动速度
   
       void step(){
           System.out.println("大敌机的y向下移动");
       }
   }
   
   package cn.tedu.shoot;
   /** 小蜜蜂 */
   public class Bee {
       int width;
       int height;
       int x;
       int y;
       int xSpeed; //x坐标移动速度
       int ySpeed; //y坐标移动速度
       int awardType; //奖励类型
   
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
   
       void step(){
           System.out.println("天空的的y和y1同时向下移动");
       }
   }
   ```

2. 创建World类，在main中：创建1个天空对象、1个英雄机对象、至少2个小敌机对象、2个大敌机对象、2个小蜜蜂对象、2个子弹对象，并给属性赋值，调用方法测试

   完整代码：

   ```java
   package cn.tedu.shoot;
   /** 整个游戏窗口 */
   public class World {
       public static void main(String[] args) {
           Sky s = new Sky();
           s.width = 400;
           s.height = 700;
           s.x = 0;
           s.y = 0;
           s.speed = 1;
           s.y1 = -700;
           s.step();
           
       	Hero h = new Hero();
           h.width = 50;
           h.height = 80;
           h.x = 100;
           h.y = 200;
           h.life = 3;
           h.fire = 0;
           h.step();
           
       	Airplane a1 = new Airplane();
           a1.width = 30;
           a1.height = 40;
           a1.x = 300;
           a1.y = 500;
           a1.speed = 2;
           a1.step();
           
       	Airplane a2 = new Airplane();
           a2.width = 30;
           a2.height = 40;
           a2.x = 100;
           a2.y = 150;
           a2.speed = 2;
           a2.step();
    
           //如下创建代码及访问代码，与上面同理----此处省略
       	BigAirplane ba1;
       	BigAirplane ba2;
       	Bee b1;
       	Bee b2;
       	Bullet bt1;
       	Bullet bt2;
       }
   }
   ```

   
