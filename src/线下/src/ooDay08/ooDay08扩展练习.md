# 面向对象第八天扩展练习：

## 列表：

1. 英雄机移动：
2. 删除越界的飞行物：
3. 设计EnemyScore得分接口、EnemyAward奖励接口，小敌机与大敌机实现EnemyScore接口，小蜜蜂实现EnemyAward接口

## 参考：

1. 英雄机移动：

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
   
       /** 英雄机移动  x/y:鼠标的x坐标和y坐标 */
       public void moveTo(int x,int y){
           this.x = x-this.width/2;  //英雄机的x=鼠标的x-1/2英雄机的宽
           this.y = y-this.height/2; //英雄机的y=鼠标的y-1/2英雄机的高
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
   import java.awt.event.MouseAdapter;
   import java.awt.event.MouseEvent;
   
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
           //鼠标侦听器
           MouseAdapter m = new MouseAdapter(){
               /** 重写mouseMoved()鼠标移动事件 */
               public void mouseMoved(MouseEvent e){
                   int x = e.getX(); //获取鼠标的x坐标
                   int y = e.getY(); //获取鼠标的y坐标
                   hero.moveTo(x,y); //英雄机随着鼠标移动
               }
           };
           this.addMouseListener(m);
           this.addMouseMotionListener(m);
           
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

2. 删除越界的飞行物：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import java.util.Random;
   import java.awt.image.BufferedImage;
   /** 飞行物 */
   public abstract class FlyingObject {
       public static final int LIVE = 0;   //活着的
       public static final int DEAD = 1;   //死了的
       public static final int REMOVE = 2; //删除的
       protected int state = LIVE; //当前状态(默认为活着的)
   
       protected int width;  //宽
       protected int height; //高
       protected int x;      //x坐标
       protected int y;      //y坐标
   
       /** 专门给小敌机、大敌机、小蜜蜂提供的 */
       //因为小敌机、大敌机、小蜜蜂的宽和高是不同的，意味着数据不能写死，所以传参
       //因为小敌机、大敌机、小蜜蜂的x和y是相同的，意味着数据可以写死
       public FlyingObject(int width,int height){
           this.width = width;
           this.height = height;
           Random rand = new Random(); //随机数对象
           x = rand.nextInt(World.WIDTH-width); //x:0到(窗口宽-敌人宽)之内的随机数
           y = -height; //y:负的敌人的高
       }
   
       /** 专门给英雄机、天空、子弹提供的 */
       //因为英雄机、天空、子弹的宽/高/x/y都是不同的，意味着数据都不能写死，所以传参
       public FlyingObject(int width,int height,int x,int y){
           this.width = width;
           this.height = height;
           this.x = x;
           this.y = y;
       }
   
       /** 飞行物移动 */
       public abstract void step();
   
       /** 获取对象的图片 */
       public abstract BufferedImage getImage();
   
       /** 判断对象是否是活着的 */
       public boolean isLive(){
           return state==LIVE; //若当前状态为LIVE，表示对象是活着的，返回true，否则返回false
       }
       /** 判断对象是否是死了的 */
       public boolean isDead(){
           return state==DEAD; //若当前状态为DEAD，表示对象是死了的，返回true，否则返回false
       }
       /** 判断对象是否是删除的 */
       public boolean isRemove(){
           return state==REMOVE; //若当前状态为REMOVE，表示对象是删除的，返回true，否则返回false
       }
   
       /** 检测敌人是否越界 */
       public boolean isOutOfBounds(){
           return y>=World.HEIGHT; //敌人的y>=窗口的高，即为越界了
       }
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   /** 子弹:是飞行物 */
   public class Bullet extends FlyingObject {
       private int speed; //移动速度
       /** 构造方法 */    //Bullet b = new Bullet(100,200);
       public Bullet(int x,int y){ //子弹可以有多个，子弹的初始坐标要依赖于当前英雄机的坐标位置
           super(8,20,x,y);
           speed = 3;
       }
   
       /** 重写step()移动 */
       public void step(){
           y-=speed; //y-(向上)
       }
   
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           if(isLive()){             //若活着的
               return Images.bullet; //则直接返回子弹图片
           }else if(isDead()) { //若死了的
               state = REMOVE;  //则将当前状态修改为REMOVE删除的
           }
           return null; //死了的和删除的，都不返回图片
           /*
             执行过程:
               1)若活着的，返回bullet图片
               2)若死了的，将当前状态修改为REMOVE删除的，同时不返回图片
               3)若删除的，不返回图片
            */
       }
   
       /** 重写isOutOfBounds()检测子弹是否越界 */
       public boolean isOutOfBounds(){
           return y<=-height; //子弹的y<=负的子弹的高，即为越界了
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
   import java.awt.event.MouseAdapter;
   import java.awt.event.MouseEvent;
   
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
       
       /** 删除越界的敌人和子弹 */
       public void outOfBoundsAction(){ //每10毫秒走一次
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               if(enemies[i].isOutOfBounds()){ //越界了
                   enemies[i] = enemies[enemies.length-1]; //将越界敌人替换为enemies的最后一个元素
                   enemies = Arrays.copyOf(enemies,enemies.length-1); //缩容
               }
           }
   
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               if(bullets[i].isOutOfBounds()){ //越界了
                   bullets[i] = bullets[bullets.length-1]; //将越界子弹替换为bullets的最后一个元素
                   bullets = Arrays.copyOf(bullets,bullets.length-1); //缩容
               }
           }
       }
   
       /** 启动程序的执行 */
       public void action(){
           //鼠标侦听器
           MouseAdapter m = new MouseAdapter(){
               /** 重写mouseMoved()鼠标移动事件 */
               public void mouseMoved(MouseEvent e){
                   int x = e.getX(); //获取鼠标的x坐标
                   int y = e.getY(); //获取鼠标的y坐标
                   hero.moveTo(x,y); //英雄机随着鼠标移动
               }
           };
           this.addMouseListener(m);
           this.addMouseMotionListener(m);
           
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask(){
               public void run(){ //定时干的事(每10毫秒走一次)
                   enterAction(); //敌人入场
                   shootAction(); //子弹入场
                   stepAction();  //飞行物移动
                   outOfBoundsAction(); //删除越界的敌人和子弹
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

3. 设计EnemyScore得分接口、EnemyAward奖励接口，小敌机与大敌机实现EnemyScore接口，小蜜蜂实现EnemyAward接口

   参考代码：

   ```java
   package cn.tedu.shoot;
   /** 得分接口 */
   public interface EnemyScore {
       /** 得分 */
       public int getScore();
   }
   
   package cn.tedu.shoot;
   /** 奖励接口 */
   public interface EnemyAward {
       public int FIRE = 0; //火力值
       public int LIFE = 1; //命
       /** 获取奖励类型(0或1) */
       public int getAwardType();
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   /** 小敌机:是飞行物，也能得分 */
   public class Airplane extends FlyingObject implements EnemyScore {
       private int speed; //移动速度
       /** 构造方法 */
       public Airplane(){
           super(48,50);
           speed = 2;
       }
   
       /** 重写step()移动 */
       public void step(){
           y+=speed; //y+(向下)
       }
   
       int index = 1; //下标
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           if(isLive()){              //若活着的
               return Images.airs[0]; //则直接返回airs[0]图片即可
           }else if(isDead()){ //若死了的
               BufferedImage img = Images.airs[index++]; //获取爆破图
               if(index==Images.airs.length){ //若index为5，则表示到最后一张爆破图了
                   state = REMOVE;            //将当前状态修改为REMOVE删除的
               }
               return img; //返回爆破图
               /*
                 执行过程:
                                     index=1
                   10M  img=airs[1]  index=2          返回airs[1]
                   20M  img=airs[2]  index=3          返回airs[2]
                   30M  img=airs[3]  index=4          返回airs[3]
                   40M  img=airs[4]  index=5(REMOVE)  返回airs[4]
                   50M  不返回图片
                */
           }
           return null; //REMOVE状态时，不返回图片
       }
   
       /** 重写getScore()得分 */
       public int getScore(){
           return 1; //打掉小敌机，玩家得1分
       }
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   /** 大敌机:是飞行物，也能得分 */
   public class BigAirplane extends FlyingObject implements EnemyScore {
       private int speed; //移动速度
       /** 构造方法 */
       public BigAirplane(){
           super(66,89);
           speed = 2;
       }
   
       /** 重写step()移动 */
       public void step(){
           y+=speed; //y+(向下)
       }
   
       int index = 1; //下标
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           if(isLive()){
               return Images.bairs[0];
           }else if(isDead()){
               BufferedImage img = Images.bairs[index++];
               if(index==Images.bairs.length){
                   state = REMOVE;
               }
               return img;
           }
           return null;
       }
   
       /** 重写getScore()得分 */
       public int getScore(){
           return 3; //打掉大敌机，玩家得3分
       }
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   import java.util.Random;
   /** 小蜜蜂:是飞行物，也是奖励 */
   public class Bee extends FlyingObject implements EnemyAward {
       private int xSpeed; //x坐标移动速度
       private int ySpeed; //y坐标移动速度
       private int awardType; //奖励类型
       /** 构造方法 */
       public Bee(){
           super(60,51);
           xSpeed = 1;
           ySpeed = 2;
           Random rand = new Random(); //随机数对象
           awardType = rand.nextInt(2); //0到1之间随机生成
       }
   
       /** 重写step()移动 */
       public void step(){
           x+=xSpeed; //x+(向右或向左)
           y+=ySpeed; //y+(向下)
           if(x<=0 || x>=World.WIDTH-width){ //若x<=0或x>=(窗口宽-蜜蜂宽)，表示到两头了
               xSpeed*=-1; //则切换方法(正变负，负变正)
           }
       }
   
       int index = 1; //下标
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           if(isLive()){
               return Images.bees[0];
           }else if(isDead()){
               BufferedImage img = Images.bees[index++];
               if(index==Images.bees.length){
                   state = REMOVE;
               }
               return img;
           }
           return null;
       }
   
       /** 重写getAwardType()获取奖励类型 */
       public int getAwardType(){
           return awardType; //返回奖励类型
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

