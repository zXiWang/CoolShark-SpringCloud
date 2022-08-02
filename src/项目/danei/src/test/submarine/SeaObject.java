package 项目.danei.src.test.submarine;

import javax.swing.*;
import java.awt.*;

/**
 * 海洋对象类 ---> 7个类的父类
 * 用于存放7个中共有的属性和方法
 */
public abstract class SeaObject {

    //活着状态
    public static final int LIVE = 0;
    //死亡状态
    public static final int DEAD = 1;

    //当前对象的状态,默认是活着
    public int currentState = LIVE;

    //共有的属性
    int x; //x坐标
    int y; //y坐标
    int width; //图片宽
    int height; //图片高
    int speed; //移动速度

    /**
     * 专门为三种潜艇提供的初始化赋值的构造方法
     * 因为潜艇的宽高不同,所以不能写死,声明成有参构造,具体的潜艇类在调用这个
     * 父类的构造方法时,传递宽高(谁调用,谁传递)
     * 创建哪个对象时,调用父类构造方法,那么父类构造方法中的this,就是那个对象!
     */
    SeaObject(int width, int height) {
        this.width = width;
        this.height = height;
        x = -width; //固定的x值,在屏幕左侧外边框
        y = (int)(Math.random()*(GameWorld.HEIGHT - height - 150) + 150); //随机产生y
        speed = (int)(Math.random()*(3 - 1) + 1); //取值1-2
    }

    //判断当前调用此方法的对象是否是死亡状态
    public boolean isDead() {
        return this.currentState == DEAD; //如果返回true,则表示死亡
    }
    //判断当前调用此方法的对象是否是活着状态
    public boolean isLive() {
        return this.currentState == LIVE; //如果返回true,则表示活着
    }

    /**
     * 为其他的四个类提供构造方法
     * 因为其他的四个类的赋值内容 x,y,width,height,speed 都不同,但是赋值的过程重复的,
     * 可以将赋值的冗余过程提取到父类的构造方法中
     * 参数的数据无法确定,做成形参即可
     */
    SeaObject(int x,int y,int width,int height,int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }


    //共有的方法
    abstract void step(); //为了向上造型

    /**
     * 父类提供的获取图片抽象方法,子类来进行重写
     * 返回值写的是图片类型
     * 因为外部调用调用这个方法时,需要图片所以应该添加返回值
     */
    public abstract ImageIcon getImage();

    /**
     * 因为每个子类都需要进行绘制,那么就将绘制的行为提取到父类中,
     * 因为绘制的逻辑功能都是一样的,所以可以声明为普通方法,让子类复用
     * 参数需要画笔对象,具体通过使用时,谁调用谁传递
     * 该方法是由具体蕾蕾对象来进行绘制
     */
    public void paintImage(Graphics g) {
        if (this.getImage() != null) { //判断当前对象获取的图片不能为空
            ImageIcon s = this.getImage(); //获取当前对象的图片
            //2.绘制的坐标
            s.paintIcon(null,g,this.x,this.y); //绘制当前对象的图片
        }
    }

    /**
     * 生成雷的方法,返回值写父类型
     * 当前这个方法后续会被水雷潜艇对象和鱼雷潜艇对象调用,也可能是侦查潜艇调用
     */
    public SeaObject shootThunder() {
        int x = this.x + this.width; //雷x
        int y = this.y - 5; //雷y

        //instanceof 关键字 : 判断当前对象的真实类型
        if(this instanceof MineSubmarine) { //判断当前对象是否是水雷潜艇对象
            return new Mine(x,y);
        }else if (this instanceof TorpedoSubmarine) { //判断当前对象是否是鱼雷潜艇对象
            return new Torpedo(x,y);
        }
        return null; //如果代码执行到这一步,说明当前对象为侦查潜艇对象
    }


    /**
     * 检测潜艇是否越界
     * 如果越界则返回true,没有越界则返回false
     */
    public boolean isOutOfBounds() {
        return this.x >= GameWorld.WIDTH; //潜艇的x>=窗口的宽,即越界了
    }

    /**
     * 检测碰撞 需要this和other两个对象完成
     * this是其中一个对象,other是另一个对象
     * @return 如果碰上了则返回true,否则返回false
     */
    public boolean isHit(SeaObject other) {
        //假设:this为潜艇,other为炸弹
        int x1 = this.x - other.width; //x1:潜艇的x - 炸弹的宽
        int x2 = this.x + this.width; //x2:潜艇的x + 潜艇的宽
        int y1 = this.y - other.height; //y1:潜艇的y - 炸弹的高
        int y2 = this.y + this.height; //yw:潜艇的y + 潜艇的高
        int x = other.x; //x:炸弹的x
        int y = other.y; //y:炸弹的y

        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public void goDead(){
        this.currentState=DEAD;
    }
}










