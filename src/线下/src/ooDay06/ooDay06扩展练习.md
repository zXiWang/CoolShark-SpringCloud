# 面向对象第六天扩展练习：

## 列表：

1. 设计窗口的宽和高为常量，在适当的地方做修改(World、FlyingObject、Sky、Bee)
2. 至少准备6个对象并画出来

## 参考：

1. 设计窗口的宽和高为常量，在适当的地方做修改(World、FlyingObject、Sky、Bee)

   参考代码：

   ```java
   package day04;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 400;  //窗口的宽
       public static final int HEIGHT = 700; //窗口的高
       
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(WIDTH+16, HEIGHT+39);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 飞行物 */
   public class FlyingObject {
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
       public void step(){ //step()的最终处理面向对象第六天讲
           System.out.println("飞行物移动啦!");
       }
   }
   
   package cn.tedu.shoot;
   /** 天空:是飞行物 */
   public class Sky extends FlyingObject {
       private int speed; //移动速度
       private int y1; //第2张图片的y坐标
       /** 构造方法 */
       public Sky(){
           super(World.WIDTH,World.HEIGHT,0,0);
           speed = 1;
           y1 = -World.HEIGHT;
       }
   
       /** 重写step()移动 */
       public void step(){
           y+=speed;  //y+(向下)
           y1+=speed; //y1+(向下)
           if(y>=World.HEIGHT){ //若y>=窗口的高，表示移到最下面了
               y=-World.HEIGHT; //则将y修改为负的窗口的高，即移到最上面去
           }
           if(y1>=World.HEIGHT){ //若y1>=窗口的高，表示移到最下面了
               y1=-World.HEIGHT; //则将y1修改为负的窗口的高，即移到最上面去
           }
       }
   }
   
   package cn.tedu.shoot;
   import java.util.Random;
   /** 小蜜蜂:是飞行物 */
   public class Bee extends FlyingObject {
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
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 至少准备6个对象并画出来

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
           y = height; //y:负的敌人的高
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
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   /** 天空:是飞行物 */
   public class Sky extends FlyingObject {
       private int speed; //移动速度
       private int y1; //第2张图片的y坐标
       /** 构造方法 */
       public Sky(){
           super(World.WIDTH,World.HEIGHT,0,0);
           speed = 1;
           y1 = -World.HEIGHT;
       }
   
       /** 重写step()移动 */
       public void step(){
           y+=speed;  //y+(向下)
           y1+=speed; //y1+(向下)
           if(y>=700){ //若y>=窗口的高，表示移到最下面了
               y=-700; //则将y修改为负的窗口的高，即移到最上面去
           }
           if(y1>=700){ //若y1>=窗口的高，表示移到最下面了
               y1=-700; //则将y1修改为负的窗口的高，即移到最上面去
           }
       }
   
       /** 重写getImage()获取图片 */
       public BufferedImage getImage(){ //每10毫秒走一次
           return Images.sky; //返回sky图片即可
       }
   
       /** 获取y1坐标 */
       public int getY1(){
           return y1; //返回y1坐标
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
           y-=speed;
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
   }
   
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
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   import java.util.Random;
   /** 小敌机:是飞行物 */
   public class Airplane extends FlyingObject {
       private int speed; //移动速度
       /** 构造方法 */
       public Airplane(){ //Airplane a = new Airplane();
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
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   import java.util.Random;
   /** 大敌机:是飞行物 */
   public class BigAirplane extends FlyingObject {
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
   }
   
   package cn.tedu.shoot;
   import java.awt.image.BufferedImage;
   import java.util.Random;
   /** 小蜜蜂:是飞行物 */
   public class Bee extends FlyingObject {
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
           if(x<=0 || x>=400-width){ //若x<=0或x>=(窗口宽-蜜蜂宽)，表示到两头了
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
   }
   
   package cn.tedu.shoot;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.awt.image.BufferedImage;
   
   /** 整个游戏窗口 */
   public class World extends JPanel {
       public static final int WIDTH = 400;  //窗口的宽
       public static final int HEIGHT = 700; //窗口的高
   
       private Sky sky = new Sky();         //天空对象
       private Hero hero = new Hero();      //英雄机对象
       private FlyingObject[] enemies = {
               new Airplane(),
               new BigAirplane(),
               new Bee()
       }; //敌人(小敌机、大敌机、小蜜蜂)数组
       private Bullet[] bullets = {
               new Bullet(100,200)
       };       //子弹数组
   
       public void action(){ //测试代码
       }
   
       /** 重写paint()画  g:画笔 */
       public void paint(Graphics g){
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
   
           world.action();
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

