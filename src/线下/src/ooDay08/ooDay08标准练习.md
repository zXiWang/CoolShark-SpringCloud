# 面向对象第八天标准练习：

## 列表：

1. 深水炸弹入场：
2. 战舰移动：
3. 删除越界的海洋对象：
4. 设计EnemyScore得分接口、EnemyLife得命接口，侦察潜艇与鱼雷潜艇实现EnemyScore接口，水雷潜艇实现EnemyLife接口

## 参考：

1. 深水炸弹入场：

   参考代码：

   ```java
   package day08;
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
   
       public Bomb shoot(){
           return new Bomb(this.x,this.y);
       }
   }
   
   package day08;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.awt.event.KeyAdapter;
   import java.awt.event.KeyEvent;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
       /** 随机生成潜艇 */
       public SeaObject nextOne(){
           Random rand = new Random();
           int type = rand.nextInt(20);
           if(type<10){
               return new ObserveSubmarine();
           }else if(type<15){
               return new TorpedoSubmarine();
           }else{
               return new MineSubmarine();
           }
       }
   
       private int subEnterIndex = 0;
       /** 潜艇入场 */
       public void submarineEnterAction(){ //每10毫秒走一次
           subEnterIndex++;
           if(subEnterIndex%40==0){ //每400毫秒
               SeaObject obj = nextOne();
               submarines = Arrays.copyOf(submarines,submarines.length+1);
               submarines[submarines.length-1] = obj;
           }
       }
       
       private int mineEnterIndex = 0;
       /** 雷(鱼雷、水雷)入场 */
       public void MineEnterAction(){
           mineEnterIndex++;
           if(mineEnterIndex%100==0){
               //如下代码暂时搁置-----第9天才讲到
           }
       }
       
       /** 海洋对象移动 */
       public void moveAction(){
           for(int i=0;i<submarines.length;i++){
               submarines[i].move();
           }
           for(int i=0;i<mines.length;i++){
               mines[i].move();
           }
           for(int i=0;i<bombs.length;i++){
               bombs[i].move();
           }
       }
   
       /** 启动程序的运行 */
       public void action(){
           KeyAdapter k = new KeyAdapter(){
               public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode() == KeyEvent.VK_SPACE){
                       Bomb obj = ship.shoot(); //深水炸弹入场
                       bombs = Arrays.copyOf(bombs,bombs.length+1);
                       bombs[bombs.length-1] = obj;
                   }
               }
           };
           this.addKeyListener(k);
           
           Timer timer = new Timer();
           int interval = 10;
           timer.schedule(new TimerTask() {
               public void run() {
                   submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
                   MineEnterAction();      //水雷入场
                   moveAction();           //海洋对象移动
                   repaint();
               }
           }, interval, interval);
       }
   
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
           world.action();
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 战舰移动

   参考代码：

   ```java
   package day08;
   import javax.swing.ImageIcon;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   
       public void move(){
           
       }
   
       public ImageIcon getImage(){
           return Images.battleship;
       }
   
       public Bomb shoot(){
           return new Bomb(this.x,this.y);
       }
   
       public void moveLeft(){
           x-=speed;
       }
       public void moveRight(){
           x+=speed;
       }
   }
   
   package day08;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.awt.event.KeyAdapter;
   import java.awt.event.KeyEvent;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
   
       /** 随机生成潜艇 */
       public SeaObject nextOne(){
           Random rand = new Random();
           int type = rand.nextInt(20);
           if(type<10){
               return new ObserveSubmarine();
           }else if(type<15){
               return new TorpedoSubmarine();
           }else{
               return new MineSubmarine();
           }
       }
   
       private int subEnterIndex = 0;
       /** 潜艇入场 */
       public void submarineEnterAction(){ //每10毫秒走一次
           subEnterIndex++;
           if(subEnterIndex%40==0){ //每400毫秒
               SeaObject obj = nextOne();
               submarines = Arrays.copyOf(submarines,submarines.length+1);
               submarines[submarines.length-1] = obj;
           }
       }
       
       private int mineEnterIndex = 0;
       /** 雷(鱼雷、水雷)入场 */
       public void MineEnterAction(){
           mineEnterIndex++;
           if(mineEnterIndex%100==0){
               //如下代码暂时搁置-----第9天才讲到
           }
       }
       
       /** 海洋对象移动 */
       public void moveAction(){
           for(int i=0;i<submarines.length;i++){
               submarines[i].move();
           }
           for(int i=0;i<mines.length;i++){
               mines[i].move();
           }
           for(int i=0;i<bombs.length;i++){
               bombs[i].move();
           }
       }
   
       /** 启动程序的运行 */
       public void action(){
           KeyAdapter k = new KeyAdapter(){
               public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode() == KeyEvent.VK_SPACE){
                       Bomb obj = ship.shoot(); //深水炸弹入场
                       bombs = Arrays.copyOf(bombs,bombs.length+1);
                       bombs[bombs.length-1] = obj;
                   }
                   if(e.getKeyCode() == KeyEvent.VK_LEFT){
                       ship.moveLeft();
                   }
                   if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                       ship.moveRight();;
                   }
               }
           };
           this.addKeyListener(k);
           
           Timer timer = new Timer();
           int interval = 10;
           timer.schedule(new TimerTask() {
               public void run() {
                   submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
                   MineEnterAction();      //水雷入场
                   moveAction();           //海洋对象移动
                   repaint();
               }
           }, interval, interval);
       }
   
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
           world.action();
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 删除越界的海洋对象:

   参考代码：

   ```java
   package day08;
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
   
       /** 检测潜艇越界 */
       public boolean isOutOfBounds(){
           return x>= World.WIDTH; //x>=窗口宽，即为越界了
       }
   }
   
   package day08;
   import javax.swing.ImageIcon;
   //水雷
   public class Mine extends SeaObject {
       public Mine(int x,int y){
           super(11,11,x,y,1);
       }
   
       public void move(){
           y-=speed;
       }
   
       public ImageIcon getImage(){
           return Images.mine;
       }
   
       /** 重写isOutOfBounds()检测水雷越界 */
       public boolean isOutOfBounds(){
           return y<=150-height; //y<=150减去水雷的高，即为越界了(到水面)
       }
   }
   
   package day08;
   import javax.swing.ImageIcon;
   //深水炸弹
   public class Bomb extends SeaObject {
       public Bomb(int x,int y){
           super(9,12,x,y,3);
       }
   
       public void move(){
           y+=speed;
       }
   
       public ImageIcon getImage(){
           return Images.bomb;
       }
   
       /** 重写isOutOfBounds()检测深水炸弹越界 */
       public boolean isOutOfBounds(){
           return y>= World.HEIGHT; //y>=窗口的高，即为越界了
       }
   }
   
   package day08;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.awt.event.KeyAdapter;
   import java.awt.event.KeyEvent;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
       /** 随机生成潜艇 */
       public SeaObject nextOne(){
           Random rand = new Random();
           int type = rand.nextInt(20);
           if(type<10){
               return new ObserveSubmarine();
           }else if(type<15){
               return new TorpedoSubmarine();
           }else{
               return new MineSubmarine();
           }
       }
   
       private int subEnterIndex = 0;
       /** 潜艇入场 */
       public void submarineEnterAction(){ //每10毫秒走一次
           subEnterIndex++;
           if(subEnterIndex%40==0){ //每400毫秒
               SeaObject obj = nextOne();
               submarines = Arrays.copyOf(submarines,submarines.length+1);
               submarines[submarines.length-1] = obj;
           }
       }
       
       private int mineEnterIndex = 0;
       /** 雷(鱼雷、水雷)入场 */
       public void MineEnterAction(){
           mineEnterIndex++;
           if(mineEnterIndex%100==0){
               //如下代码暂时搁置-----第8天才讲到
           }
       }
       
       /** 海洋对象移动 */
       public void moveAction(){
           for(int i=0;i<submarines.length;i++){
               submarines[i].move();
           }
           for(int i=0;i<mines.length;i++){
               mines[i].move();
           }
           for(int i=0;i<bombs.length;i++){
               bombs[i].move();
           }
       }
       
       /** 删除越界的对象 */
       public void outOfBoundsAction(){
           for(int i=0;i<submarines.length;i++){
               if(submarines[i].isOutOfBounds()){
                   submarines[i] = submarines[submarines.length-1];
                   submarines = Arrays.copyOf(submarines,submarines.length-1);
               }
           }
   
           for(int i=0;i<mines.length;i++){
               if(mines[i].isOutOfBounds()){
                   mines[i] = mines[mines.length-1];
                   mines = Arrays.copyOf(mines,mines.length-1);
               }
           }
   
           for(int i=0;i<bombs.length;i++){
               if(bombs[i].isOutOfBounds()){
                   bombs[i] = bombs[bombs.length-1];
                   bombs = Arrays.copyOf(bombs,bombs.length-1);
               }
           }
       }
   
       /** 启动程序的运行 */
       public void action(){
           KeyAdapter k = new KeyAdapter(){
               public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode() == KeyEvent.VK_SPACE){
                       Bomb obj = ship.shoot(); //深水炸弹入场
                       bombs = Arrays.copyOf(bombs,bombs.length+1);
                       bombs[bombs.length-1] = obj;
                   }
                   if(e.getKeyCode() == KeyEvent.VK_LEFT){
                       ship.moveLeft();
                   }
                   if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                       ship.moveRight();
                   }
               }
           };
           this.addKeyListener(k);
           
           Timer timer = new Timer();
           int interval = 10;
           timer.schedule(new TimerTask() {
               public void run() {
                   submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
                   MineEnterAction();      //水雷入场
                   moveAction();           //海洋对象移动
                   outOfBoundsAction();    //删除越界的对象
                   repaint();
               }
           }, interval, interval);
       }
   
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
           world.action();
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

4. 设计EnemyScore得分接口、EnemyLife得命接口，侦察潜艇与鱼雷潜艇实现EnemyScore接口，水雷潜艇实现EnemyLife接口

   参考代码：

   ```java
   package day08;
   //得分接口
   public interface EnemyScore {
       public int getScore();
   }
   
   package day08;
   //奖励接口
   public interface EnemyLife {
       public int getLife();
   }
   
   package day08;
   import javax.swing.ImageIcon;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject implements EnemyScore {
       public ObserveSubmarine(){
           super(63,19);
       }
   
       public void move(){
           x+=speed;
       }
   
       public ImageIcon getImage(){
           return Images.obsersubm;
       }
   
       public int getScore(){
           return 10;
       }
   }
   
   package day08;
   import javax.swing.ImageIcon;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject implements EnemyScore {
       public TorpedoSubmarine(){
           super(64,20);
       }
   
       public void move(){
           x+=speed;
       }
   
       public ImageIcon getImage(){
           return Images.torpesubm;
       }
   
       public int getScore(){
           return 40;
       }
   }
   
   package day08;
   import javax.swing.ImageIcon;
   //水雷潜艇
   public class MineSubmarine extends SeaObject implements EnemyLife {
       public MineSubmarine(){
           super(63,19);
       }
   
       public void move(){
           x+=speed;
       }
   
       public ImageIcon getImage(){
           return Images.minesubm;
       }
   
       public Mine shootMine(){
           int x = this.x+this.width; //x:潜艇的x+潜艇的宽
           int y = this.y-5;          //y:潜艇的y-5
           return new Mine(x,y);
       }
   
       public int getLife(){
           return 1;
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

