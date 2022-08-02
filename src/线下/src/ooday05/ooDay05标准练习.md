# 面向对象第五天标准练习：

## 列表：

1. 给所有类中的成员添加访问控制修饰符
2. 设计Images图片类，设计战舰图片、侦察潜艇图片、鱼雷潜艇图片、水雷潜艇图片、水雷图片、深水炸弹图片、海洋图片、游戏结束图片，并在静态块中赋值，设计main方法测试图片是否读取成功



## 参考：

1. 给所有类中的成员添加访问控制修饰符

   参考代码：

   ```java
   package day05;
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
           y = rand.nextInt(479-height-150+1)+150;
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
   
   package day05;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
       public void move(){
           //暂时搁置
       }
   }
   
   package day05;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject {
       public ObserveSubmarine(){
           super(63,19);
       }
       public void move(){
           x+=speed;
       }
   }
   
   package day05;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject {
       public TorpedoSubmarine(){
           super(64,20);
       }
       public void move(){
           x+=speed;
       }
   }
   
   package day05;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       public MineSubmarine(){
           super(63,19);
       }
       public void move(){
           x+=speed;
       }
   }
   
   package day05;
   //水雷
   public class Mine extends SeaObject {
       public Mine(int x,int y){
           super(11,11,x,y,1);
       }
       public void move(){
           y-=speed;
       }
   }
   
   package day05;
   //深水炸弹
   public class Bomb extends SeaObject {
       public Bomb(int x,int y){
           super(9,12,x,y,3);
       }
       public void move(){
           y+=speed;
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 设计Images图片类，设计战舰图片、侦察潜艇图片、鱼雷潜艇图片、水雷潜艇图片、水雷图片、深水炸弹图片、海洋图片、游戏结束图片，并在静态块中赋值，设计main方法测试图片是否读取成功

   参考代码：

   ```java
   package day05;
   import javax.swing.ImageIcon;
   //图片类
   public class Images {
       public static ImageIcon battleship; //战舰
       public static ImageIcon obsersubm;  //侦察潜艇
       public static ImageIcon torpesubm;  //鱼雷潜艇
       public static ImageIcon minesubm;   //水雷潜艇
       public static ImageIcon mine;       //水雷
       public static ImageIcon bomb;       //深水炸弹
       public static ImageIcon sea;        //海洋图
       public static ImageIcon gameover;   //游戏结束图
       static{
           battleship = new ImageIcon("img/battleship.png");
           obsersubm = new ImageIcon("img/obsersubm.png");
           torpesubm = new ImageIcon("img/torpesubm.png");
           minesubm = new ImageIcon("img/minesubm.png");
           mine = new ImageIcon("img/mine.png");
           bomb = new ImageIcon("img/bomb.png");
           sea = new ImageIcon("img/sea.png");
           gameover = new ImageIcon("img/gameover.png");
       }
   
       public static void main(String[] args){
           System.out.println(battleship.getImageLoadStatus());
           System.out.println(obsersubm.getImageLoadStatus());
           System.out.println(torpesubm.getImageLoadStatus());
           System.out.println(minesubm.getImageLoadStatus());
           System.out.println(mine.getImageLoadStatus());
           System.out.println(bomb.getImageLoadStatus());
           System.out.println(sea.getImageLoadStatus());
           System.out.println(gameover.getImageLoadStatus());
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

