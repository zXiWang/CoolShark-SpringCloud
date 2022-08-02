# 面向对象第六天标准练习：

## 列表：

1. 设计窗口的宽和高为常量，在适当的地方做修改(World类、SeaObject)
2. 画海洋图、至少准备6个对象并画出来

## 参考：

1. 设计窗口的宽和高为常量，在适当的地方做修改(World类、SeaObject)

   参考代码：

   ```java
   package day06;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
       
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
   
   package day06;
   import java.util.Random;
   //海洋对象
   public class SeaObject {
       protected int width;
       protected int height;
       protected int x;
       protected int y;
       protected int speed;
       public SeaObject(int width,int height){
           this.width = width;
           this.height = height;
           x = -width;
           Random rand = new Random();
           y = rand.nextInt(World.HEIGHT-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       public SeaObject(int width,int height,int x,int y,int speed){
           this.width = width;
           this.height = height;
           this.x = x;
           this.y = y;
           this.speed = speed;
       }
   
       public void move(){
           System.out.println("海洋对象移动");
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 画海洋图、至少准备6个对象并画出来

   参考代码：

   ```java
   package day06;
   import javax.swing.ImageIcon;
   import java.awt.Graphics;
   import java.util.Random;
   //海洋对象
   public abstract class SeaObject {
       public static final int LIVE = 0;
       public static final int DEAD = 1;
       protected int state = LIVE; //当前状态
   
       protected int width;
       protected int height;
       protected int x;
       protected int y;
       protected int speed;
       public SeaObject(int width,int height){
           this.width = width;
           this.height = height;
           x = width;
           Random rand = new Random();
           y = rand.nextInt(World.HEIGHT-height-150+1)+150;
           speed = rand.nextInt(3)+1;
       }
       public SeaObject(int width,int height,int x,int y,int speed){
           this.width = width;
           this.height = height;
           this.x = x;
           this.y = y;
           this.speed = speed;
       }
   
       public abstract void move();
   
       public abstract ImageIcon getImage();
   
       public boolean isLive(){
           return state==LIVE;
       }
       public boolean isDead(){
           return state==DEAD;
       }
   
       public void paintImage(Graphics g){
           if(isLive()){
               this.getImage().paintIcon(null,g,x,y);
           }
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   
       public void move(){
           System.out.println("战舰移动");
       }
   
       public ImageIcon getImage(){
           return Images.battleship;
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject {
       public ObserveSubmarine(){
           super(63,19);
       }
   
       public void move(){
           System.out.println("侦察潜艇x向右移动");
       }
   
       public ImageIcon getImage(){
           return Images.obsersubm;
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject {
       public TorpedoSubmarine(){
           super(64,20);
       }
   
       public void move(){
           System.out.println("鱼雷潜艇x向右移动");
       }
   
       public ImageIcon getImage(){
           return Images.torpesubm;
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       public MineSubmarine(){
           super(63,19);
       }
   
       public void move(){
           System.out.println("水雷潜艇x向右移动");
       }
   
       public ImageIcon getImage(){
           return Images.minesubm;
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //水雷
   public class Mine extends SeaObject {
       public Mine(int x,int y){
           super(11,11,x,y,1);
       }
   
       public void move(){
           System.out.println("水雷y向上移动");
       }
   
       public ImageIcon getImage(){
           return Images.mine;
       }
   }
   
   package day06;
   import javax.swing.ImageIcon;
   //深水炸弹
   public class Bomb extends SeaObject {
       public Bomb(int x,int y){
           super(9,12,x,y,3);
       }
   
       public void move(){
           System.out.println("深水炸弹y向下移动");
       }
   
       public ImageIcon getImage(){
           return Images.bomb;
       }
   }
   
   package day06;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {
               new ObserveSubmarine(),
               new TorpedoSubmarine(),
               new MineSubmarine()
       }; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {
               new Mine(260,200)
       }; //水雷
       private Bomb[] bombs = {
               new Bomb(200,190)
       }; //深水炸弹
   
       public void paint(Graphics g){
           Images.sea.paintIcon(null,g,0,0); //画海洋图
           ship.paintImage(g);
           for(int i=0;i<submarines.length;i++){
               submarines[i].paintImage(g);
           }
           for(int i=0;i<mines.length;i++){
               mines[i].paintImage(g);
           }
           for(int i=0;i<bombs.length;i++){
               bombs[i].paintImage(g);
           }
       }
   
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
   
   //注:其余类没有变化，此处省略
   ```

   
