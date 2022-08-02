# 面向对象第七天扩展练习：

## 列表：

1. 敌人(小敌机、大敌机、小蜜蜂)入场：
2. 子弹入场：
3. 飞行物移动：

## 参考：

1. 敌人(小敌机、大敌机、小蜜蜂)入场：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.util.Random;
   import java.util.Arrays;
   
   /** 整个游戏窗口 */
   public class World extends JPanel {
       public static final int WIDTH = 400;  //窗口的宽
       public static final int HEIGHT = 700; //窗口的高
   
       //如下对象为窗口中所显示的对象
       private Sky sky = new Sky();         //天空对象
       private Hero hero = new Hero();      //英雄机对象
       private FlyingObject[] enemies = {}; //敌人(小敌机、大敌机、小蜜蜂)数组
       private Bullet[] bullets = {};       //子弹数组
   
       /** 生成敌人(小敌机、大敌机、小蜜蜂)对象 */
       public FlyingObject nextOne(){
           Random rand = new Random(); //随机数对象
           int type = rand.nextInt(20); //0到19之间的随机数
           if(type<5){ //0到4时，返回小蜜蜂对象
               return new Bee();
           }else if(type<13){ //5到12时，返回小敌机对象
               return new Airplane();
           }else{ //13到19时，返回大敌机对象
               return new BigAirplane();
           }
       }
   
       private int enterIndex = 0; //敌人入场计数
       /** 敌人入场 */
       public void enterAction(){ //每10毫秒走一次
           enterIndex++; //每10毫秒增1
           if(enterIndex%40==0){ //每400(40*10)毫秒走一次
               FlyingObject obj = nextOne(); //获取敌人对象
               enemies = Arrays.copyOf(enemies,enemies.length+1); //扩容
               enemies[enemies.length-1] = obj; //将obj添加到enemies的最后一个元素上
           }
       }
   	
       /** 启动程序的执行 */
       public void action(){
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask(){
               public void run(){ //定时干的事(每10毫秒走一次)
                   enterAction(); //敌人入场
                   repaint(); //重画(重新调用paint()方法)
               }
           },interval,interval); //定时计划表
       }
   
       /** 重写paint()画  g:画笔 */
       public void paint(Graphics g){ //每10毫秒走一次
           g.drawImage(sky.getImage(),sky.x,sky.y,null); //画天空
           g.drawImage(sky.getImage(),sky.x,sky.getY1(),null); //画天空2
           g.drawImage(hero.getImage(),hero.x,hero.y,null); //画英雄机
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               g.drawImage(f.getImage(),f.x,f.y,null); //画敌人
           }
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               g.drawImage(b.getImage(),b.x,b.y,null); //画子弹
           }
       }
   
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(WIDTH,HEIGHT);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法
   
           world.action(); //启动程序的执行
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 子弹入场：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   /** 英雄机:是飞行物 */
   public class Hero extends FlyingObject {
       private int life;   //命
       private int fire;   //火力值
       /** 构造方法 */
       public Hero(){
           super(97,139,140,400);
           life = 3;
           fire = 0;
       }
   
       /** 重写step()移动 */
       public void step(){
           
       }
   
       int index = 0; //下标
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           return Images.heros[index++%Images.heros.length]; //heros[0]和heros[1]来回切换
           /* 执行过程:
                                 index=0
              10M  返回heros[0]  index=1
              20M  返回heros[1]  index=2
              30M  返回heros[0]  index=3
              40M  返回heros[1]  index=4
              50M  返回heros[0]  index=5
            */
       }
   
       /** 英雄机发射子弹(生成子弹对象) */
       public Bullet[] shoot(){
           int xStep = this.width/4; //1/4英雄机的宽
           int yStep = 20; //固定的20
           if(fire>0){ //双倍
               Bullet[] bs = new Bullet[2]; //2发子弹
               bs[0] = new Bullet(this.x+1*xStep,this.y-yStep); //x:英雄机的x+1/4英雄机的宽  y:英雄机的y-固定的20
               bs[1] = new Bullet(this.x+3*xStep,this.y-yStep); //x:英雄机的x+3/4英雄机的宽  y:英雄机的y-固定的20
               fire-=2; //发射一次双倍火力，则火力值减2
               return bs;
           }else{ //单倍
               Bullet[] bs = new Bullet[1]; //1发子弹
               bs[0] = new Bullet(this.x+2*xStep,this.y-yStep); //x:英雄机的x+2/4英雄机的宽  y:英雄机的y-固定的20
               return bs;
           }
       }
   }
   
   package cn.tedu.shoot;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.util.Random;
   import java.util.Arrays;
   
   /** 整个游戏窗口 */
   public class World extends JPanel {
       public static final int WIDTH = 400;  //窗口的宽
       public static final int HEIGHT = 700; //窗口的高
   
       //如下对象为窗口中所显示的对象
       private Sky sky = new Sky();         //天空对象
       private Hero hero = new Hero();      //英雄机对象
       private FlyingObject[] enemies = {}; //敌人(小敌机、大敌机、小蜜蜂)数组
       private Bullet[] bullets = {};       //子弹数组
   
       /** 生成敌人(小敌机、大敌机、小蜜蜂)对象 */
       public FlyingObject nextOne(){
           Random rand = new Random(); //随机数对象
           int type = rand.nextInt(20); //0到19之间的随机数
           if(type<5){ //0到4时，返回小蜜蜂对象
               return new Bee();
           }else if(type<13){ //5到12时，返回小敌机对象
               return new Airplane();
           }else{ //13到19时，返回大敌机对象
               return new BigAirplane();
           }
       }
   
       private int enterIndex = 0; //敌人入场计数
       /** 敌人入场 */
       public void enterAction(){ //每10毫秒走一次
           enterIndex++; //每10毫秒增1
           if(enterIndex%40==0){ //每400(40*10)毫秒走一次
               FlyingObject obj = nextOne(); //获取敌人对象
               enemies = Arrays.copyOf(enemies,enemies.length+1); //扩容
               enemies[enemies.length-1] = obj; //将obj添加到enemies的最后一个元素上
           }
       }
   
       private int shootIndex = 0; //子弹入场计数
       /** 子弹入场 */
       public void shootAction(){ //每10毫秒走一次
           shootIndex++; //每10毫秒增1
           if(shootIndex%30==0){ //每300(30*10)毫秒走一次
               Bullet[] bs = hero.shoot(); //获取子弹数组对象
               bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个就扩大几个容量)
               System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
           }
       }
   
       /** 启动程序的执行 */
       public void action(){
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask(){
               public void run(){ //定时干的事(每10毫秒走一次)
                   enterAction(); //敌人入场
                   shootAction(); //子弹入场
                   System.out.println(enemies.length+","+bullets.length);
                   repaint(); //重画(重新调用paint()方法)
               }
           },interval,interval); //定时计划表
       }
   
       /** 重写paint()画  g:画笔 */
       public void paint(Graphics g){ //每10毫秒走一次
           g.drawImage(sky.getImage(),sky.x,sky.y,null); //画天空
           g.drawImage(sky.getImage(),sky.x,sky.getY1(),null); //画天空2
           g.drawImage(hero.getImage(),hero.x,hero.y,null); //画英雄机
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               g.drawImage(f.getImage(),f.x,f.y,null); //画敌人
           }
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               g.drawImage(b.getImage(),b.x,b.y,null); //画子弹
           }
       }
   
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(WIDTH,HEIGHT);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法
   
           world.action(); //启动程序的执行
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 飞行物移动：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.util.Random;
   import java.util.Arrays;
   
   /** 整个游戏窗口 */
   public class World extends JPanel {
       public static final int WIDTH = 400;  //窗口的宽
       public static final int HEIGHT = 700; //窗口的高
   
       //如下对象为窗口中所显示的对象
       private Sky sky = new Sky();         //天空对象
       private Hero hero = new Hero();      //英雄机对象
       private FlyingObject[] enemies = {}; //敌人(小敌机、大敌机、小蜜蜂)数组
       private Bullet[] bullets = {};       //子弹数组
   
       /** 生成敌人(小敌机、大敌机、小蜜蜂)对象 */
       public FlyingObject nextOne(){
           Random rand = new Random(); //随机数对象
           int type = rand.nextInt(20); //0到19之间的随机数
           if(type<5){ //0到4时，返回小蜜蜂对象
               return new Bee();
           }else if(type<13){ //5到12时，返回小敌机对象
               return new Airplane();
           }else{ //13到19时，返回大敌机对象
               return new BigAirplane();
           }
       }
   
       private int enterIndex = 0; //敌人入场计数
       /** 敌人入场 */
       public void enterAction(){ //每10毫秒走一次
           enterIndex++; //每10毫秒增1
           if(enterIndex%40==0){ //每400(40*10)毫秒走一次
               FlyingObject obj = nextOne(); //获取敌人对象
               enemies = Arrays.copyOf(enemies,enemies.length+1); //扩容
               enemies[enemies.length-1] = obj; //将obj添加到enemies的最后一个元素上
           }
       }
   
       private int shootIndex = 0; //子弹入场计数
       /** 子弹入场 */
       public void shootAction(){ //每10毫秒走一次
           shootIndex++; //每10毫秒增1
           if(shootIndex%30==0){ //每300(30*10)毫秒走一次
               Bullet[] bs = hero.shoot(); //获取子弹数组对象
               bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个就扩大几个容量)
               System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
           }
       }
       
       /** 飞行物移动 */
       public void stepAction(){ //每10毫秒走一次
           sky.step(); //天空动
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               enemies[i].step(); //敌人动
           }
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               bullets[i].step(); //子弹动
           }
       }
   
       /** 启动程序的执行 */
       public void action(){
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask(){
               public void run(){ //定时干的事(每10毫秒走一次)
                   enterAction(); //敌人入场
                   shootAction(); //子弹入场
                   stepAction();  //飞行物移动
                   repaint(); //重画(重新调用paint()方法)
               }
           },interval,interval); //定时计划表
       }
   
       /** 重写paint()画  g:画笔 */
       public void paint(Graphics g){ //每10毫秒走一次
           g.drawImage(sky.getImage(),sky.x,sky.y,null); //画天空
           g.drawImage(sky.getImage(),sky.x,sky.getY1(),null); //画天空2
           g.drawImage(hero.getImage(),hero.x,hero.y,null); //画英雄机
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               g.drawImage(f.getImage(),f.x,f.y,null); //画敌人
           }
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               g.drawImage(b.getImage(),b.x,b.y,null); //画子弹
           }
       }
   
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(WIDTH,HEIGHT);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法
   
           world.action(); //启动程序的执行
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

