# 面向对象第七天标准练习：

## 列表：

1. 潜艇入场：
2. 水雷入场：
3. 海洋对象移动：

## 参考：

1. 潜艇入场：

   参考代码：

   ```java
   package day07;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
       /** 随机生成潜艇 */
       public SeaObject nextSubmarine(){
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
               SeaObject obj = nextSubmarine();
               submarines = Arrays.copyOf(submarines,submarines.length+1);
               submarines[submarines.length-1] = obj;
           }
       }
   
       /** 启动程序的运行 */
       public void action(){
           Timer timer = new Timer();
           int interval = 10;
           timer.schedule(new TimerTask() {
               public void run() {
                   submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
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

2. 水雷入场：

   ```java
   package day07;
   import javax.swing.ImageIcon;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       public MineSubmarine(){
           super(63,19);
       }
   
       public void move(){
           x+=speed;
       }
   
       public ImageIcon getImage(){
           return Images.minesubm;
       }
   
       /** 生成水雷对象 */
       public Mine shootMine(){
           int x = this.x+this.width; //x:潜艇的x+潜艇的宽
           int y = this.y-5;          //y:潜艇的y-5
           return new Mine(x,y);
       }
   }
   
   package day07;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
       /** 随机生成潜艇 */
       public SeaObject nextSubmarine(){
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
               SeaObject obj = nextSubmarine();
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
   
       /** 启动程序的运行 */
       public void action(){
           Timer timer = new Timer();
           int interval = 10;
           timer.schedule(new TimerTask() {
               public void run() {
                   submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
                   MineEnterAction();      //水雷入场
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

3. 海洋对象移动：

   完整代码：

   ```java
   package day07;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   //整个游戏世界
   public class World extends JPanel {
       public static final int WIDTH = 641;
       public static final int HEIGHT = 479;
   
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
       private Mine[] mines = {}; //水雷
       private Bomb[] bombs = {}; //深水炸弹
   
       /** 随机生成潜艇 */
       public SeaObject nextSubmarine(){
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
               SeaObject obj = nextSubmarine();
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
