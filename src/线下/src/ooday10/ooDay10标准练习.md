

# 面向对象第十天标准练习：

## 列表：

1. 水雷与战舰的碰撞：
2. 检测游戏结束：
3. 画状态：
4. String基本练习：
    声明最少三个字符串型变量并直接赋值，使用==比较三个变量是否相等，修改其中一个变量的值，
    使用==比较修改后的变量与其它两个变量是否相等。



## 参考：

1. 水雷与战舰的碰撞:

   参考代码：

   ```java
   package day10;
   import javax.swing.ImageIcon;
   //战舰
   public class Battleship extends SeaObject {
       private int life;
       public Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
   
       public void step(){
           
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
       
       public void subtractLife(){
           life--;
       }
   }
   
   package day10;
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
       
       //水雷与战舰的碰撞
       public void mineBangAction(){
           for(int i=0;i<mines.length;i++){
               Mine m = mines[i];
               if(m.isLive() && ship.isLive() && m.isHit(ship)){
                   m.goDead();
                   ship.subtractLife();
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
                   mineBangAction();       //水雷与战舰的碰撞
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
   
2. 检测游戏结束：

   参考代码：

   ```java
   package day10;
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
       
       //水雷与战舰的碰撞
       public void mineBangAction(){
           for(int i=0;i<mines.length;i++){
               Mine m = mines[i];
               if(m.isLive() && ship.isLive() && m.isHit(ship)){
                   m.goDead();
                   ship.subtractLife();
               }
           }
       }
       
       /** 检测游戏结束 */
       public void checkGameOverAction(){
           if(ship.getLife()<=0){
               ......
           }
       }
   
       /** 启动程序的运行 */
       public void action(){
           KeyAdapter k = new KeyAdapter(){
               public void keyPressed(KeyEvent e) {
                   if(e.getKeyCode() == KeyEvent.VK_SPACE){
                       Bomb obj = ship.shoot();
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
                   outOfBoundsAction();    //删除越界的对象
                   bombBangAction();       //深水炸弹与潜艇的碰撞
                   mineBangAction();       //水雷与战舰的碰撞
                   checkGameOverAction();  //检测游戏结束
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

3. 画状态：

   参考代码：

   ```java
   package cn.tedu.submarine;
   import javax.swing.JFrame;
   import javax.swing.JPanel; //1.
   import java.awt.Graphics;
   import java.util.Arrays;
   import java.util.Random;
   import java.util.Timer;
   import java.util.TimerTask;
   import java.awt.event.KeyAdapter;
   import java.awt.event.KeyEvent;
   
   /** 整个游戏世界 */
   public class World extends JPanel { //2.
       public static final int WIDTH = 641;  //窗口的宽
       public static final int HEIGHT = 479; //窗口的高
   
       public static final int RUNNING = 0;   //运行状态
       public static final int PAUSE = 1;     //暂停状态
       public static final int GAME_OVER = 2; //游戏结束状态
       private int state = RUNNING; //当前状态(默认为运行状态)
   
       //如下这一堆为窗口中所显示的对象
       private Battleship ship = new Battleship(); //战舰
       private SeaObject[] submarines = {};        //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)数组
       private Mine[] mines = {};                  //水雷数组
       private Bomb[] bombs = {};                  //深水炸弹数组
   
       /** 生成潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)对象 */
       private SeaObject nextSubmarine(){
           Random rand = new Random(); //随机数对象
           int type = rand.nextInt(20); //0到19之间
           if(type<10){ //0到9时，返回侦察潜艇
               return new ObserveSubmarine();
           }else if(type<15){ //10到14时，返回鱼雷潜艇
               return new TorpedoSubmarine();
           }else{ //15到19时，返回水雷潜艇
               return new MineSubmarine();
           }
       }
   
       private int subEnterIndex = 0; //潜艇入场计数
       /** 潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)入场 */
       private void submarineEnterAction(){ //每10毫秒走一次
           subEnterIndex++; //每10毫秒增1
           if(subEnterIndex%40==0){ //每400(40*10)毫秒走一次
               SeaObject obj = nextSubmarine(); //获取潜艇对象
               submarines = Arrays.copyOf(submarines,submarines.length+1); //扩容
               submarines[submarines.length-1] = obj; //将obj添加到submarines最后一个元素上
           }
       }
   
       private int mineEnterIndex = 0; //水雷入场计数
       /** 水雷入场 */
       private void mineEnterAction(){ //每10毫秒走一次
           mineEnterIndex++; //每10毫秒增1
           if(mineEnterIndex%100==0){ //每1000(100*10)毫秒走一次
               for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
                   if(submarines[i] instanceof MineSubmarine){ //若潜艇为水雷潜艇
                       MineSubmarine ms = (MineSubmarine)submarines[i]; //将潜艇强转为水雷潜艇
                       Mine obj = ms.shootMine(); //获取水雷对象
                       mines = Arrays.copyOf(mines,mines.length+1); //扩容
                       mines[mines.length-1] = obj; //将obj添加到mines最后一个元素上
                   }
               }
           }
       }
   
       /** 海洋对象移动 */
       private void moveAction(){ //每10毫秒走一次
           for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
               submarines[i].move(); //潜艇移动
           }
           for(int i=0;i<mines.length;i++){ //遍历所有水雷
               mines[i].move(); //水雷移动
           }
           for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
               bombs[i].move(); //深水炸弹移动
           }
       }
   
       /** 删除越界的海洋对象----避免内存泄漏 */
       private void outOfBoundsAction(){ //每10毫秒走一次
           for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
               if(submarines[i].isOutOfBounds() || submarines[i].isDead()){ //越界的，或者死了的
                   submarines[i] = submarines[submarines.length-1]; //将越界潜艇替换为最后一个元素
                   submarines = Arrays.copyOf(submarines,submarines.length-1); //缩容
               }
           }
   
           for(int i=0;i<mines.length;i++){ //遍历所有水雷
               if(mines[i].isOutOfBounds() || mines[i].isDead()){ //越界的，或者死了的
                   mines[i] = mines[mines.length-1]; //将越界水雷替换为最后一个元素
                   mines = Arrays.copyOf(mines,mines.length-1); //缩容
               }
           }
   
           for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
               if(bombs[i].isOutOfBounds() || bombs[i].isDead()){ //越界的，或者死了的
                   bombs[i] = bombs[bombs.length-1]; //将越界深水炸弹替换为最后一个元素
                   bombs = Arrays.copyOf(bombs,bombs.length-1); //缩容
               }
           }
       }
   
       private int score = 0; //玩家得分
       /** 深水炸弹与潜艇的碰撞 */
       private void bombBangAction(){ //每10毫秒走一次
           for(int i=0;i<bombs.length;i++){ //遍历所有炸弹
               Bomb b = bombs[i]; //获取每个炸弹
               for(int j=0;j<submarines.length;j++){ //遍历所有潜艇
                   SeaObject s = submarines[j]; //获取每个潜艇
                   if(b.isLive() && s.isLive() && s.isHit(b)){ //若都活着并且还撞上了
                       s.goDead(); //潜艇去死
                       b.goDead(); //炸弹去死
   
                       if(s instanceof EnemyScore){ //若被撞对象为分
                           EnemyScore es = (EnemyScore)s; //将被撞对象强转为得分接口
                           score += es.getScore(); //玩家得分
                       }
                       if(s instanceof EnemyLife){ //若被撞对象为命
                           EnemyLife el = (EnemyLife)s; //将被撞对象强转为得命接口
                           int num = el.getLife(); //获取命数
                           ship.addLife(num); //战舰增命
                       }
                   }
               }
           }
       }
   
       /** 水雷与战舰的碰撞 */
       private void mineBangAction(){ //每10毫秒走一次
           for(int i=0;i<mines.length;i++){ //遍历所有水雷
               Mine m = mines[i]; //获取每个水雷
               if(m.isLive() && ship.isLive() && ship.isHit(m)){ //若都活着并且还撞上了
                   m.goDead(); //水雷去死
                   ship.subtractLife(); //减命
               }
           }
       }
   
       /** 检测游戏结束 */
       private void checkGameOverAction(){ //每10毫秒走一次
           if(ship.getLife()<=0){ //若战舰的命数<=0，表示游戏结束了
               state = GAME_OVER; //将当前状态修改为GAME_OVER游戏结束状态
           }
       }
   
       /** 启动程序的执行 */
       private void action(){
           //键盘侦听器----不要求掌握
           KeyAdapter k = new KeyAdapter() {
               /** 重写keyReleased按键抬起事件 keyPressed()键盘按下事件 */
               public void keyReleased(KeyEvent e) { //当按键抬起时会自动触发---不要求掌握
                   if(e.getKeyCode()==KeyEvent.VK_P){ //若抬起的是P键
                       if(state==RUNNING){ //运行状态时修改为暂停状态
                           state = PAUSE;
                       }else if(state==PAUSE){ //暂停状态时修改为运行状态
                           state = RUNNING;
                       }
                   }
   
                   if(state==RUNNING){ //仅在运行状态时执行
                       if(e.getKeyCode()==KeyEvent.VK_SPACE){ //若抬起的是空格键---不要求掌握
                           Bomb obj = ship.shootBomb(); //获取炸弹对象
                           bombs = Arrays.copyOf(bombs,bombs.length+1); //扩容
                           bombs[bombs.length-1] = obj; //将obj添加到bombs的最后一个元素上
                       }
                       if(e.getKeyCode()==KeyEvent.VK_LEFT){ //若抬起的是左箭头----不要求掌握
                           ship.moveLeft(); //战舰左移
                       }
                       if(e.getKeyCode()==KeyEvent.VK_RIGHT){ //若抬起的是右箭头----不要求掌握
                           ship.moveRight();
                       }
                   }
               }
           };
           this.addKeyListener(k); //添加侦听----不要求掌握
   
           Timer timer = new Timer(); //定时器对象
           int interval = 10; //定时间隔(以毫秒为单位)
           timer.schedule(new TimerTask() {
               public void run() { //定时干的事(每10毫秒自动执行)
                   if(state==RUNNING){ //仅在运行状态下执行
                       submarineEnterAction(); //潜艇(侦察艇、鱼雷艇、水雷艇)入场
                       mineEnterAction();      //水雷入场
                       moveAction();           //海洋对象移动
                       outOfBoundsAction();    //删除越界的海洋对象
                       bombBangAction();       //炸弹与潜艇的碰撞
                       mineBangAction();       //水雷与战舰的碰撞
                       checkGameOverAction();  //检测游戏结束
                       repaint(); //重画(重新调用paint()方法)---不要求掌握
                   }
               }
           }, interval, interval); //计划表/日程表
       }
   
       /** 重写JPanel类中的paint()方法 g:画笔 */  //只需要知道想画东西需要重写paint()即可
       public void paint(Graphics g){ //每10毫秒走一次
           Images.sea.paintIcon(null,g,0,0); //画海洋图---不要求掌握
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
           g.drawString("SCORE: "+score,200,50); //不要求掌握--画分
           g.drawString("LIFE: "+ship.getLife(),400,50); //不要求掌握--画命
   
           if(state==GAME_OVER){ //若当前状态为游戏结束状态
               Images.gameover.paintIcon(null,g,0,0); //画游戏结束图---不要求掌握
           }
       }
   
       public static void main(String[] args) {
           JFrame frame = new JFrame(); //3.
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(WIDTH+16, HEIGHT+39);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true); //自动调用paint()方法
   
           world.action();
       }
   }
   
   //注:其余类没有变化，此处省略
   ```
4. String基本练习：
   声明最少三个字符串型变量并直接赋值，使用==比较三个变量是否相等，修改其中一个变量的值，
   使用==比较修改后的变量与其它两个变量是否相等。

   参考代码：

   ```java
   /*
     使用字面量创建字符串对象时，JVM会检查常量池中是否有该对象:
     1)若没有，则会创建该字符串对象并存入常量池中
     2)若有，则直接将常量池中的对象地址返回(不会再创建新的字符串对象)
   */
   String s1 = "123abc"; //常量池还没有，因此创建该字符串对象，并存入常量池
   String s2 = "123abc"; //常量池中已经有了，直接重用对象
   String s3 = "123abc"; //常量池中已经有了，直接重用对象
   //引用类型==，比较的是地址是否相同   //练习-----5:10继续
   System.out.println(s1==s2); //true
   System.out.println(s1==s3); //true
   System.out.println(s2==s3); //true
   s1 = s1+"!"; //创建新的字符串对象并将地址赋值给s1
   System.out.println(s1==s2); //false，因为s1为新对象的地址，与s2不同了
   
   ```
   
   

