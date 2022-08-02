package 项目.danei.src.first.submarine;

/**
 * 海洋对象类 ---> 7个类的父类
 * 用于存放7个中共有的属性和方法
 */
public class SeaObject {
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
        y = (int)(Math.random()*(479 - height - 150) + 150); //随机产生y
        speed = (int)(Math.random()*(3 - 1) + 1); //取值1-2
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
    void step() { //移动方法
        System.out.println("海洋对象在移动...");
    }
}
