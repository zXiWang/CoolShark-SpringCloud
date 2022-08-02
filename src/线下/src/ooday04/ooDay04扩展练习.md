# 面向对象第四天扩展练习：

## 列表：

1. 将小敌机数组、大敌机数组、小蜜蜂数组统一组合为FlyingObject数组，给数组填充数据，遍历数组输出属性并调用step()方法
2. 在6个类中重写step()移动方法，实现对象的具体移动
3. 画窗口

## 参考：

1. 将小敌机数组、大敌机数组、小蜜蜂数组统一组合为FlyingObject数组，给数组填充数据，遍历数组输出属性并调用step()方法

   参考代码：

   ```java
   package cn.tedu.shoot;
   /** 整个游戏窗口 */
   public class World{
       public static void main(String[] args) {
           FlyingObject[] enemies = new FlyingObject[5];
           enemies[0] = new Airplane(); //向上造型
           enemies[1] = new Airplane();
           enemies[2] = new BigAirplane();
           enemies[3] = new BigAirplane();
           enemies[4] = new Bee();
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每个敌人
               System.out.println(f.x+","+f.y);
               f.step();
           }
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 在6个类中重写step()移动方法，实现对象的具体移动

   参考代码：

   ```java
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
   
       /** 重写step()移动 */
       void step(){
           //暂时搁置
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小敌机:是飞行物 */
   public class Airplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       Airplane(){ //Airplane a = new Airplane();
           super(48,50);
           speed = 2;
       }
   
       /** 重写step()移动 */
       void step(){
           y+=speed; //y+(向下)
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 大敌机:是飞行物 */
   public class BigAirplane extends FlyingObject {
       int speed; //移动速度
       /** 构造方法 */
       BigAirplane(){
           super(66,89);
           speed = 2;
       }
   
       /** 重写step()移动 */
       void step(){
           y+=speed; //y+(向下)
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
   
       /** 重写step()移动 */
       void step(){
           x+=xSpeed; //x+(向右或向左)
           y+=ySpeed; //y+(向下)
           if(x<=0 || x>=400-width){ //若x<=0或x>=(窗口宽-蜜蜂宽)，表示到两头了
               xSpeed*=-1; //则切换方法(正变负，负变正)
           }
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
   
       /** 重写step()移动 */
       void step(){
           y-=speed;
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
   
       /** 重写step()移动 */
       void step(){
           y+=speed;  //y+(向下)
           y1+=speed; //y1+(向下)
           if(y>=700){ //若y>=窗口的高，表示移到最下面了
               y=-700; //则将y修改为负的窗口的高，即移到最上面去
           }
           if(y1>=700){ //若y1>=窗口的高，表示移到最下面了
               y1=-700; //则将y1修改为负的窗口的高，即移到最上面去
           }
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 画窗口：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   /** 整个游戏窗口 */
   public class World extends JPanel{
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(400+16,700+39);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

