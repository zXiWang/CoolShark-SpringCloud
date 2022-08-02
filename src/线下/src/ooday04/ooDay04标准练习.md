# 面向对象第四天标准练习：

## 列表：

1. 将侦察艇数组、鱼雷艇数组、水雷艇数组统一组合为SeaObject数组，给数组填充数据，遍历数组输出属性并调用move()方法
2. 在6个类中重写move()移动方法，实现对象的具体移动，并测试
3. 画窗口

## 参考：

1. 将侦察艇数组、鱼雷艇数组、水雷艇数组统一组合为SeaObject数组，给数组填充数据，遍历数组输出属性并调用move()方法

   参考代码：

   ```java
   package day04;
   //整个游戏世界
   public class World {
       public static void main(String[] args) {
           SeaObject[] submarines = new SeaObject[5]; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
           submarines[0] = new ObserveSubmarine();
           submarines[1] = new ObserveSubmarine();
           submarines[2] = new TorpedoSubmarine();
           submarines[3] = new TorpedoSubmarine();
           submarines[4] = new MineSubmarine();
           for(int i=0;i<submarines.length;i++){
               SeaObject s = submarines[i];
               System.out.println(s.x+","+s.y);
               s.move();
           }
   
           Battleship s = new Battleship();
   
           Mine[] mines = new Mine[2];
           mines[0] = new Mine(100,200);
           mines[1] = new Mine(200,400);
           for(int i=0;i<mines.length;i++){
               Mine m = mines[i];
               System.out.println(m.x+","+m.y);
               m.move();
           }
   
           Bomb[] bombs = new Bomb[2];
           bombs[0] = new Bomb(100,200);
           bombs[1] = new Bomb(200,400);
           for(int i=0;i<bombs.length;i++){
               Bomb b = bombs[i];
               System.out.println(b.x+","+b.y);
               b.move();
           }
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 在6个类中重写move()移动方法，实现对象的具体移动，并测试

   参考代码：

   ```java
   package day04;
   //战舰
   public class Battleship extends SeaObject {
       int life;
       Battleship(){
           super(66,26,270,124,20);
           life = 5;
       }
       void move(){
           //暂时搁置
       }
   }
   
   package day04;
   //侦察潜艇
   public class ObserveSubmarine extends SeaObject {
       ObserveSubmarine(){
           super(63,19);
       }
       void move(){
           x+=speed;
       }
   }
   
   package day04;
   //鱼雷潜艇
   public class TorpedoSubmarine extends SeaObject {
       TorpedoSubmarine(){
           super(64,20);
       }
       void move(){
           x+=speed;
       }
   }
   
   package day04;
   //水雷潜艇
   public class MineSubmarine extends SeaObject {
       MineSubmarine(){
           super(63,19);
       }
       void move(){
           x+=speed;
       }
   }
   
   package day04;
   //水雷
   public class Mine extends SeaObject {
       Mine(int x,int y){
           super(11,11,x,y,1);
       }
       void move(){
           y-=speed;
       }
   }
   
   package day04;
   //深水炸弹
   public class Bomb extends SeaObject {
       Bomb(int x,int y){
           super(9,12,x,y,3);
       }
       void move(){
           y+=speed;
       }
   }
   
   package day04;
   /** 整个游戏窗口 */
   public class World {
       public static void main(String[] args) {
           SeaObject[] submarines = new SeaObject[5]; //潜艇数组
           submarines[0] = new ObserveSubmarine(); //向上造型
           submarines[1] = new ObserveSubmarine();
           submarines[2] = new TorpedoSubmarine();
           submarines[3] = new TorpedoSubmarine();
           submarines[4] = new MineSubmarine();
           for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
               System.out.println("第"+(i+1)+"个潜艇:");
               SeaObject s = submarines[i]; //获取每一个潜艇
               System.out.println("初始的坐标:"+s.x+","+s.y);
               s.move(); //移动
               System.out.println("移动后坐标:"+s.x+","+s.y); //移动后坐标
           }
   
           Mine[] mines = new Mine[4];
           mines[0] = new Mine(100,200);
           mines[1] = new Mine(110,120);
           mines[2] = new Mine(130,140);
           mines[3] = new Mine(140,150);
           for(int i=0;i<mines.length;i++){ //遍历所有水雷
               System.out.println("第"+(i+1)+"个水雷:");
               Mine m = mines[i];  //获取每一个水雷
               System.out.println("初始的坐标:"+m.x+","+m.y);
               m.move();
               System.out.println("移动后坐标:"+m.x+","+m.y);
           }
   
           Bomb[] bombs = new Bomb[2];
           bombs[0] = new Bomb(100,200);
           bombs[1] = new Bomb(200,185);
           for(int i=0;i<bombs.length;i++){
               System.out.println("第"+(i+1)+"个深水炸弹:");
               Bomb b = bombs[i];
               System.out.println("初始的坐标:"+b.x+","+b.y);
               b.move();
               System.out.println("移动后坐标:"+b.x+","+b.y);
           }
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

3. 画窗口：

   参考代码：

   ```java
   package day04;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   //整个游戏世界
   public class World extends JPanel {
       public static void main(String[] args) {
           JFrame frame = new JFrame();
           World world = new World();
           world.setFocusable(true);
           frame.add(world);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(641+16, 479+39);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);
       }
   }
   
   //注:其余类没有变化，此处省略
   ```

   
