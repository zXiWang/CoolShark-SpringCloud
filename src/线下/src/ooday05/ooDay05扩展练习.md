# 面向对象第五天扩展练习：

## 列表：

1. 给所有类中的成员添加访问控制修饰符

2. 设计Images图片类，设计天空图片、子弹图片、英雄机图片数组、小敌机图片数组、大敌机图片数组、小蜜蜂图片数组，并在静态块中赋值

   ```java
   注意事项:
   1.图片需要装cn.tedu.shoot包中(与那一堆.java文件要装在一起)
   2.Images类中的readImage()方法直接复制粘贴(此方法不要求掌握)
   3.看参考代码中Images中的main方法来测试，如果程序不报错就是正常的，如果报错:
     1)检查图片名称是否正确
     2)检查数组是否new了
     3)如果前两步确认正确，则将idea关闭，然后重新打开，再运行。
   注意：晚上做扩展练习的Images类时，若按照上面的注意事项还是解决不了，就暂时先不用管了，周日再讲
   ```
   
   



## 参考：

1. 给所有类中的成员添加访问控制修饰符

   参考代码：

   ```java
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
           x = rand.nextInt(400-width); //x:0到(窗口宽-敌人宽)之内的随机数
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
           //搁置
       }
   }
   
   package cn.tedu.shoot;
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
   }
   
   package cn.tedu.shoot;
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
           if(x<=0 || x>=400-width){ //若x<=0或x>=(窗口宽-蜜蜂宽)，表示到两头了
               xSpeed*=-1; //则切换方法(正变负，负变正)
           }
       }
   }
   
   package cn.tedu.shoot;
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
   }
   
   package cn.tedu.shoot;
   /** 天空:是飞行物 */
   public class Sky extends FlyingObject {
       private int speed; //移动速度
       private int y1; //第2张图片的y坐标
       /** 构造方法 */
       public Sky(){
           super(400,700,0,0);
           speed = 1;
           y1 = -700;
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
   }
   
   //注:其余类没有变化，此处省略
   ```

2. 设计Images图片类，设计天空图片、子弹图片、英雄机图片数组、小敌机图片数组、大敌机图片数组、小蜜蜂图片数组，并在静态块中赋值

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
   
       static { //给图片赋值
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
   
   //注:其余类没有变化，此处省略
   ```
