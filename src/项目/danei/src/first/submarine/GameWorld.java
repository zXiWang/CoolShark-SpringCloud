package 项目.danei.src.first.submarine;


import javax.swing.JFrame; //窗口
import javax.swing.JPanel; //面板

/**
 * 游戏运行测试类
 *
 * 1.为什么要讲各类的变量声明在main方法外部?
 *   在main方法中声明的变量,作用域只在main中,但是后期当前类中其他位置也需要使用,
 *   所以应该将这些变量设计为成员变量
 * 2.为什么要单独创建action方法来创建对象?
 *   因为main方法使用static关键字修饰的静态方法,静态方法无法直接访问类成员
 * 3.为什么要创建GameWorld类,再调用action方法?
 *   因为main方法使用static关键字修饰的静态方法,静态方法无法直接访问类成员,所以
 *   通过创建类对象的形式,通过对象来调用(.)action方法
 */
public class GameWorld extends JPanel {
    Battleship ship = new Battleship();
    Bomb[] bombs = {}; //{}相当于为当前数组创建数组对象,并没有开辟空间,避免空指针
    SeaObject[] submarines = {}; //代表三种潜艇(水雷潜艇,鱼雷潜艇,侦查潜艇)
    SeaObject[] thunder = {}; //代表两种雷(水雷和鱼雷)


    void action() {
        submarines = new SeaObject[9];
        submarines[0] = new ObserveSubmarine();
        submarines[1] = new ObserveSubmarine();
        submarines[2] = new ObserveSubmarine();
        submarines[3] = new MineSubmarine();
        submarines[4] = new MineSubmarine();
        submarines[5] = new MineSubmarine();
        submarines[6] = new TorpedoSubmarine();
        submarines[7] = new TorpedoSubmarine();
        submarines[8] = new TorpedoSubmarine();
        for (int i = 0; i < submarines.length; i++) { //遍历数组
            submarines[i].step(); //调用数组中潜艇对象的移动行为
            //发射雷的行为
        }

        thunder = new SeaObject[2];
        thunder[0] = new Mine(10,29);
        thunder[1] = new Torpedo(10,29);
        for (int i = 0; i < thunder.length; i++) {
            thunder[i].step();
            //攻击战舰的行为
        }

    }

    /**
     * 绘制窗口的代码
     */
    void paintWorld() {
        JFrame frame = new JFrame(); //创建窗口
        setFocusable(true); //可聚焦的
        frame.add(this); //把面板添加到窗口中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口结束程序
        frame.setSize(641+16,479+39); //窗口尺寸
        frame.setLocationRelativeTo(null); //窗口居中呈现
        frame.setVisible(true); //可视化
    }

    public static void main(String[] args) {
        GameWorld gw = new GameWorld();
        gw.paintWorld();
        gw.action();
    }
}
