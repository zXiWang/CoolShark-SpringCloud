# 面向对象第九天标准练习：

## 列表：

1. 深水炸弹入场(下半段)：

2. 深水炸弹与潜艇的碰撞：

3. 画分和画命：



## 参考：

1. 深水炸弹入场(下半段)：

   参考代码：

   ```java
   package day09;
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
               for(int i=0;i<submarines.length;i++){
                   if(submarines[i] instanceof MineSubmarine){
                       MineSubmarine ms = (MineSubmarine)submarines[i];
                       Mine obj = ms.shootMine();
                       mines = Arrays.copyOf(mines,mines.length+1);
                       mines[mines.length-1] = obj;
                   }
               }
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

2. 深水炸弹与潜艇的碰撞：

   参考代码：

   ```java
   package day09;
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
       
       /** 检测碰撞 */
       public boolean isHit(SeaObject other){
           //假设this指代潜艇，other指代炸弹
           int x1 = this.x-other.width;  //x1:潜艇的x-炸弹的宽
           int x2 = this.x+this.width;   //x2:潜艇的x+潜艇的宽
           int y1 = this.y-other.height; //y1:潜艇的y-炸弹的高
           int y2 = this.y+this.height;  //y2:潜艇的y+潜艇的高
           int x = other.x; //x:炸弹的x
           int y = other.y; //y:炸弹的y
   
           return x>=x1 && x<=x2
                  &&
                  y>=y1 && y<=y2; //x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
       }
   
       /** 飞行物去死 */
       public void goDead(){
           state = DEAD; //将状态修改为DEAD死了的
       }
   }
   
   package day09;
   import javax.swing.ImageIcon;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   
       public void step(){
           System.out.println("战舰移动");
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
   
       public void addLife(int life){
           this.life += life;
       }
   }
   
   package day09;
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
               for(int i=0;i<submarines.length;i++){
                   if(submarines[i] instanceof MineSubmarine){
                       MineSubmarine ms = (MineSubmarine)submarines[i];
                       Mine obj = ms.shootMine();
                       mines = Arrays.copyOf(mines,mines.length+1);
                       mines[mines.length-1] = obj;
                   }
               }
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
               if(submarines[i].isOutOfBounds() || submarines[i].isDead()){
                   submarines[i] = submarines[submarines.length-1];
                   submarines = Arrays.copyOf(submarines,submarines.length-1);
               }
           }
   
           for(int i=0;i<mines.length;i++){
               if(mines[i].isOutOfBounds() || mines[i].isDead()){
                   mines[i] = mines[mines.length-1];
                   mines = Arrays.copyOf(mines,mines.length-1);
               }
           }
   
           for(int i=0;i<bombs.length;i++){
               if(bombs[i].isOutOfBounds() || bombs[i].isDead()){
                   bombs[i] = bombs[bombs.length-1];
                   bombs = Arrays.copyOf(bombs,bombs.length-1);
               }
           }
       }
       
       private int score = 0; //玩家得分
       //深水炸弹与潜艇的碰撞
       public void bombBangAction(){
           for(int i=0;i<bombs.length;i++){
               Bomb b = bombs[i];
               for(int j=0;j<submarines.length;j++){
                   SeaObject s = submarines[j];
                   if(b.isLive() && s.isLive() && s.isHit(b)){
                       b.goDead();
                       s.goDead();
                       if(s instanceof EnemyScore){
                           EnemyScore es = (EnemyScore)s;
                           score += es.getScore();
                       }
                       if(s instanceof EnemyLife){
                           EnemyLife ea = (EnemyLife)s;
                           int life = ea.getLife();
                           ship.addLife(life);
                       }
                   }
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
                   bombBangAction();       //深水炸弹与潜艇的碰撞
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

3. 画分和画命：

   参考代码：

   ```java
   package day09;
   import javax.swing.ImageIcon;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   
       public void step(){
           System.out.println("战舰移动");
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
   
       public void addLife(int life){
           this.life += life;
       }
       
       public int getLife(){
           return life;
       }
   }
   
   package day09;
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
               for(int i=0;i<submarines.length;i++){
                   if(submarines[i] instanceof MineSubmarine){
                       MineSubmarine ms = (MineSubmarine)submarines[i];
                       Mine obj = ms.shootMine();
                       mines = Arrays.copyOf(mines,mines.length+1);
                       mines[mines.length-1] = obj;
                   }
               }
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
               if(submarines[i].isOutOfBounds() || submarines[i].isDead()){
                   submarines[i] = submarines[submarines.length-1];
                   submarines = Arrays.copyOf(submarines,submarines.length-1);
               }
           }
   
           for(int i=0;i<mines.length;i++){
               if(mines[i].isOutOfBounds() || mines[i].isDead()){
                   mines[i] = mines[mines.length-1];
                   mines = Arrays.copyOf(mines,mines.length-1);
               }
           }
   
           for(int i=0;i<bombs.length;i++){
               if(bombs[i].isOutOfBounds() || bombs[i].isDead()){
                   bombs[i] = bombs[bombs.length-1];
                   bombs = Arrays.copyOf(bombs,bombs.length-1);
               }
           }
       }
       
       private int score = 0; //玩家得分
       //深水炸弹与潜艇的碰撞
       public void bombBangAction(){
           for(int i=0;i<bombs.length;i++){
               Bomb b = bombs[i];
               for(int j=0;j<submarines.length;j++){
                   SeaObject s = submarines[j];
                   if(b.isLive() && s.isLive() && s.isHit(b)){
                       b.goDead();
                       s.goDead();
                       if(s instanceof EnemyScore){
                           EnemyScore es = (EnemyScore)s;
                           score += es.getScore();
                       }
                       if(s instanceof EnemyLife){
                           EnemyLife ea = (EnemyLife)s;
                           int life = ea.getLife();
                           ship.addLife(life);
                       }
                   }
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
                   bombBangAction();       //深水炸弹与潜艇的碰撞
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
           
           g.drawString("SCORE: "+score,200,50);
           g.drawString("LIFE: "+ship.getLife(),400,50);
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

   
