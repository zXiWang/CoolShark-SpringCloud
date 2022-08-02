# 面向对象第十天扩展练习：

## 列表：

1. 英雄机与敌人的碰撞：
2. 检测游戏结束：
3. 画状态：



## 参考：

1. 英雄机与敌人的碰撞：

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
   
       /** 英雄机增命 */
       public void addLife(){
           life++; //命数增1
       }
       /** 获取英雄机的命 */
       public int getLife(){
           return life; //返回命数
       }
       /** 英雄机减命 */
       public void subtractLife(){
           life--; //命数减1
       }
   
       /** 英雄机增火力 */
       public void addFire(){
           fire+=40; //火力值增40
       }
       /** 清空火力值 */
       public void clearFire(){
           fire=0; //火力值归零
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
               if(enemies[i].isOutOfBounds() || enemies[i].isRemove()){ //越界了
                   enemies[i] = enemies[enemies.length-1]; //将越界敌人替换为enemies的最后一个元素
                   enemies = Arrays.copyOf(enemies,enemies.length-1); //缩容
               }
           }
   
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               if(bullets[i].isOutOfBounds() || bullets[i].isRemove()){ //越界了
                   bullets[i] = bullets[bullets.length-1]; //将越界子弹替换为bullets的最后一个元素
                   bullets = Arrays.copyOf(bullets,bullets.length-1); //缩容
               }
           }
       }
   
       private int score = 0; //玩家的得分
       /** 子弹与敌人的碰撞 */
       public void bulletBangAction(){ //每10毫秒走一次
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               for(int j=0;j<enemies.length;j++){ //遍历所有敌人
                   FlyingObject f = enemies[j]; //获取每一个敌人
                   if(b.isLive() && f.isLive() && f.isHit(b)){ //若都活着的，并且还撞上了
                       b.goDead(); //子弹去死
                       f.goDead(); //敌人去死
                       if(f instanceof EnemyScore){ //若被撞对象能得分
                           EnemyScore es = (EnemyScore)f; //将被撞对象强转为得分接口
                           score += es.getScore(); //玩家得分
                       }
                       if(f instanceof EnemyAward){ //若被撞对象为奖励
                           EnemyAward ea = (EnemyAward)f; //将被撞对象强转为奖励接口
                           int type = ea.getAwardType(); //获取奖励类型
                           switch (type){ //根据奖励类型来获取不同的奖励
                               case EnemyAward.FIRE: //若为火力值
                                   hero.addFire();   //则英雄机增火力
                                   break;
                               case EnemyAward.LIFE: //若为命
                                   hero.addLife();   //则英雄机增命
                                   break;
                           }
                       }
                   }
               }
           }
       }
       
        /** 英雄机与敌人的碰撞 */
       public void heroBangAction(){ //每10毫秒走一次
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               if(f.isLive() && hero.isLive() && f.isHit(hero)){ //若都活着并且还撞上了
                   f.goDead(); //敌人去死
                   hero.subtractLife(); //英雄机减命
                   hero.clearFire(); //英雄机清空火力值
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
                   bulletBangAction();  //子弹与敌人的碰撞
                   heroBangAction();    //英雄机与敌人的碰撞
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
   
           g.drawString("SCORE: "+score,10,25); //画分
           g.drawString("LIFE: "+hero.getLife(),10,45); //画命
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

2. 检测游戏结束：

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
               if(enemies[i].isOutOfBounds() || enemies[i].isRemove()){ //越界了
                   enemies[i] = enemies[enemies.length-1]; //将越界敌人替换为enemies的最后一个元素
                   enemies = Arrays.copyOf(enemies,enemies.length-1); //缩容
               }
           }
   
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               if(bullets[i].isOutOfBounds() || bullets[i].isRemove()){ //越界了
                   bullets[i] = bullets[bullets.length-1]; //将越界子弹替换为bullets的最后一个元素
                   bullets = Arrays.copyOf(bullets,bullets.length-1); //缩容
               }
           }
       }
   
       private int score = 0; //玩家的得分
       /** 子弹与敌人的碰撞 */
       public void bulletBangAction(){ //每10毫秒走一次
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               for(int j=0;j<enemies.length;j++){ //遍历所有敌人
                   FlyingObject f = enemies[j]; //获取每一个敌人
                   if(b.isLive() && f.isLive() && f.isHit(b)){ //若都活着的，并且还撞上了
                       b.goDead(); //子弹去死
                       f.goDead(); //敌人去死
                       if(f instanceof EnemyScore){ //若被撞对象能得分
                           EnemyScore es = (EnemyScore)f; //将被撞对象强转为得分接口
                           score += es.getScore(); //玩家得分
                       }
                       if(f instanceof EnemyAward){ //若被撞对象为奖励
                           EnemyAward ea = (EnemyAward)f; //将被撞对象强转为奖励接口
                           int type = ea.getAwardType(); //获取奖励类型
                           switch (type){ //根据奖励类型来获取不同的奖励
                               case EnemyAward.FIRE: //若为火力值
                                   hero.addFire();   //则英雄机增火力
                                   break;
                               case EnemyAward.LIFE: //若为命
                                   hero.addLife();   //则英雄机增命
                                   break;
                           }
                       }
                   }
               }
           }
       }
       
        /** 英雄机与敌人的碰撞 */
       public void heroBangAction(){ //每10毫秒走一次
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               if(f.isLive() && hero.isLive() && f.isHit(hero)){ //若都活着并且还撞上了
                   f.goDead(); //敌人去死
                   hero.subtractLife(); //英雄机减命
                   hero.clearFire(); //英雄机清空火力值
               }
           }
       }
       
       /** 检测游戏结束 */
       public void checkGameOverAction(){ //每10毫秒走一次
           if(hero.getLife()<=0){ //若英雄机的命数<=0，表示游戏结束了
               
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
                   bulletBangAction();  //子弹与敌人的碰撞
                   heroBangAction();    //英雄机与敌人的碰撞
                   checkGameOverAction(); //检测游戏结束
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
   
           g.drawString("SCORE: "+score,10,25); //画分
           g.drawString("LIFE: "+hero.getLife(),10,45); //画命
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

3. 画状态：

   参考代码：

   ```java
   package cn.tedu.shoot;
   import javax.imageio.ImageIO;
   import java.awt.image.BufferedImage;
   /** 图片工具类 */
   public class Images {
   //  公开的  静态的   图片数据类型  变量名
       public static BufferedImage sky;      //天空图片
       public static BufferedImage bullet;   //子弹图片
       public static BufferedImage[] heros;  //英雄机图片数组
       public static BufferedImage[] airs;   //小敌机图片数组
       public static BufferedImage[] bairs;  //大敌机图片数组
       public static BufferedImage[] bees;   //小蜜蜂图片数组
       public static BufferedImage start;    //启动图
       public static BufferedImage pause;    //暂停图
       public static BufferedImage gameover; //游戏结束图
   
       static { //给图片赋值
           start = readImage("start.png");
           pause = readImage("pause.png");
           gameover = readImage("gameover.png");
   
           sky = readImage("background.png");
           bullet = readImage("bullet.png");
           heros = new BufferedImage[2]; //2张图片
           heros[0] = readImage("hero0.png");
           heros[1] = readImage("hero1.png");
   
           airs = new BufferedImage[5]; //5张图片
           bairs = new BufferedImage[5]; //5张图片
           bees = new BufferedImage[5]; //5张图片
           airs[0] = readImage("airplane.png");
           bairs[0] = readImage("bigairplane.png");
           bees[0] = readImage("bee.png");
           for(int i=1;i<airs.length;i++){ //赋值爆破图
               airs[i] = readImage("bom"+i+".png");
               bairs[i] = readImage("bom"+i+".png");
               bees[i] = readImage("bom"+i+".png");
           }
       }
   
       /** 读取图片  fileName:图片名称 */
       public static BufferedImage readImage(String fileName){
           try{
               BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName)); //读取与FlyingObject同包中的图片
               return img;
           }catch(Exception e){
               e.printStackTrace();
               throw new RuntimeException();
           }
       }
   
       public static void main(String[] args) {
           System.out.println(Images.heros.length); //测试代码只要用到Images类就OK
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
   
       public static final int START = 0;     //启动状态
       public static final int RUNNING = 1;   //运行状态
       public static final int PAUSE = 2;     //暂停状态
       public static final int GAME_OVER = 3; //游戏结束状态
       private int state = START; //当前状态(默认为启动状态)
   
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
   
       /** 删除越界的敌人和子弹----避免内存泄漏 */
       public void outOfBoundsAction(){ //每10毫秒走一次
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               if(enemies[i].isOutOfBounds() || enemies[i].isRemove()){ //越界了或者删除状态的
                   enemies[i] = enemies[enemies.length-1]; //将越界敌人替换为enemies的最后一个元素
                   enemies = Arrays.copyOf(enemies,enemies.length-1); //缩容
               }
           }
   
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               if(bullets[i].isOutOfBounds() || bullets[i].isRemove()){ //越界了或者删除状态的
                   bullets[i] = bullets[bullets.length-1]; //将越界子弹替换为bullets的最后一个元素
                   bullets = Arrays.copyOf(bullets,bullets.length-1); //缩容
               }
           }
       }
   
       private int score = 0; //玩家的得分
       /** 子弹与敌人的碰撞 */
       public void bulletBangAction(){ //每10毫秒走一次
           for(int i=0;i<bullets.length;i++){ //遍历所有子弹
               Bullet b = bullets[i]; //获取每一个子弹
               for(int j=0;j<enemies.length;j++){ //遍历所有敌人
                   FlyingObject f = enemies[j]; //获取每一个敌人
                   if(b.isLive() && f.isLive() && f.isHit(b)){ //若都活着的，并且还撞上了
                       b.goDead(); //子弹去死
                       f.goDead(); //敌人去死
                       if(f instanceof EnemyScore){ //若被撞对象能得分
                           EnemyScore es = (EnemyScore)f; //将被撞对象强转为得分接口
                           score += es.getScore(); //玩家得分
                       }
                       if(f instanceof EnemyAward){ //若被撞对象为奖励
                           EnemyAward ea = (EnemyAward)f; //将被撞对象强转为奖励接口
                           int type = ea.getAwardType(); //获取奖励类型
                           switch (type){ //根据奖励类型来获取不同的奖励
                               case EnemyAward.FIRE: //若为火力值
                                   hero.addFire();   //则英雄机增火力
                                   break;
                               case EnemyAward.LIFE: //若为命
                                   hero.addLife();   //则英雄机增命
                                   break;
                           }
                       }
                   }
               }
           }
       }
   
       /** 英雄机与敌人的碰撞 */
       public void heroBangAction(){ //每10毫秒走一次
           for(int i=0;i<enemies.length;i++){ //遍历所有敌人
               FlyingObject f = enemies[i]; //获取每一个敌人
               if(f.isLive() && hero.isLive() && f.isHit(hero)){ //若都活着并且还撞上了
                   f.goDead(); //敌人去死
                   hero.subtractLife(); //英雄机减命
                   hero.clearFire(); //英雄机清空火力值
               }
           }
       }
   
       /** 检测游戏结束 */
       public void checkGameOverAction(){ //每10毫秒走一次
           if(hero.getLife()<=0){ //若英雄机的命数<=0，表示游戏结束了
               state = GAME_OVER; //将当前状态修改为游戏结束状态
           }
       }
   
       /** 启动程序的执行 */
       public void action(){
           //鼠标侦听器
           MouseAdapter m = new MouseAdapter(){
               /** 重写mouseMoved()鼠标移动事件 */
               public void mouseMoved(MouseEvent e){
                   if(state==RUNNING) { //仅在运行状态下执行
                       int x = e.getX(); //获取鼠标的x坐标
                       int y = e.getY(); //获取鼠标的y坐标
                       hero.moveTo(x, y); //英雄机随着鼠标移动
                   }
               }
               /** 重写mouseClicked()鼠标点击事件 */
               public void mouseClicked(MouseEvent e){
                   switch(state){ //根据当前状态来做不同的处理
                       case START:          //启动状态时
                           state = RUNNING; //修改为运行状态
                           break;
                       case GAME_OVER:    //游戏结束状态时
                           score = 0;        //清理现场
                           sky = new Sky();
                           hero = new Hero();
                           enemies = new FlyingObject[0];
                           bullets = new Bullet[0];
                           state = START; //修改为启动状态
                           break;
                   }
               }
               /** 重写mouseExited()鼠标移出事件 */
               public void mouseExited(MouseEvent e){
                   if(state==RUNNING){ //运行状态时
                       state = PAUSE;  //修改为暂停状态
                   }
               }
               /** 重写mouseEntered()鼠标移入事件 */
               public void mouseEntered(MouseEvent e){
                   if(state==PAUSE){    //暂停状态时
                       state = RUNNING; //修改为运行状态
                   }
               }
           };
           this.addMouseListener(m);
           this.addMouseMotionListener(m);
   
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask(){
               public void run(){ //定时干的事(每10毫秒走一次)
                   if(state==RUNNING){ //仅在运行状态下执行
                       enterAction(); //敌人入场
                       shootAction(); //子弹入场
                       stepAction();  //飞行物移动
                       outOfBoundsAction();   //删除越界的敌人和子弹
                       bulletBangAction();    //子弹与敌人的碰撞
                       heroBangAction();      //英雄机与敌人的碰撞
                       checkGameOverAction(); //检测游戏结束
                   }
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
   
           g.drawString("SCORE: "+score,10,25); //画分
           g.drawString("LIFE: "+hero.getLife(),10,45); //画命
   
           switch (state){ //根据当前状态来画不同的状态图
               case START: //启动状态时画启动图
                   g.drawImage(Images.start,0,0,null);
                   break;
               case PAUSE: //暂停状态时画暂停图
                   g.drawImage(Images.pause,0,0,null);
                   break;
               case GAME_OVER: //游戏结束状态时画游戏结束图
                   g.drawImage(Images.gameover,0,0,null);
                   break;
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

