package 项目.danei.src.test.submarine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.TimerTask; //任务模板
import java.util.Timer; //定时器模板

/**
 * 游戏运行测试类
 * <p>
 * 1.为什么要讲各类的变量声明在main方法外部?
 * 在main方法中声明的变量,作用域只在main中,但是后期当前类中其他位置也需要使用,
 * 所以应该将这些变量设计为成员变量
 * 2.为什么要单独创建action方法来创建对象?
 * 因为main方法使用static关键字修饰的静态方法,静态方法无法直接访问类成员
 * 3.为什么要创建GameWorld类,再调用action方法?
 * 因为main方法使用static关键字修饰的静态方法,静态方法无法直接访问类成员,所以
 * 通过创建类对象的形式,通过对象来调用(.)action方法
 */
public class GameWorld extends JPanel {
    public static final int WIDTH = 641; //背景宽
    public static final int HEIGHT = 479; //背景高

    Battleship ship = new Battleship();
    Bomb[] bombs = {}; //{}相当于为当前数组创建数组对象,并没有开辟空间,避免空指针
    SeaObject[] submarines = {}; //代表三种潜艇(水雷潜艇,鱼雷潜艇,侦查潜艇)
    SeaObject[] thunder = {}; //代表两种雷(水雷和鱼雷)
    int score = 0;
    /**
     * 潜艇入场的方法:内部主要是实现的逻辑就是将产生的潜艇对象封装到submarines数组中
     */
    private int submarineEnterIndex = 0; //变量声明的原因控制潜艇的产生速度
    //雷入场的方法   控制雷产生的速度,每1000毫秒执行一次
    private int thunderEnterIndex = 0; //通过此变量控制雷产生的速度

    public static void main(String[] args) {
        GameWorld gw = new GameWorld();
        gw.paintWorld();
        gw.action();
    }

    /**
     * 生成潜艇的方法
     * 返回值写成父类,会更加通用
     */
    public SeaObject createSubmarine() {
        //产生0~20之间的随机数
        int type = (int) (Math.random() * 20);
        //当产生的数据小于10,返回侦查潜艇
        if (type < 10) {
            return new ObserveSubmarine();
        } else if (type < 15) {//当产生的数据小于15,返回鱼雷潜艇
            return new TorpedoSubmarine();
        } else {//否则,返回水雷潜艇
            return new MineSubmarine();
        }
    }

    public void submarineEnterAction() { //该方法放到run()中调用
        /**
         * 1.调用生成潜艇的方法,返回一个对象
         * 2.为潜艇数组在原有的基础上进行扩容
         * 3.将产生的对象赋值给潜艇数组的最后一个元素
         */
        submarineEnterIndex++;
        if (submarineEnterIndex % 40 == 0) { //每400毫秒执行一次
            SeaObject s = createSubmarine();
            submarines = Arrays.copyOf(submarines, submarines.length + 1);
            submarines[submarines.length - 1] = s;
        }
    }

    public void thunderEnterAction() {
        /**
         * 1.for循环遍历submarines数组,调用数组中每个对象的shootThunder方法
         * 2.接收调用方法时返回的对象(雷对象)
         * 3.判断接收的雷对象不等于null
         * 4.为thunder扩容
         * 5.将雷对象装载到数组最后一个元素中
         */
        thunderEnterIndex++;
        if (thunderEnterIndex % 100 == 0) {
            for (int i = 0; i < submarines.length; i++) {
                SeaObject t = submarines[i].shootThunder();
                if (t != null) {
                    thunder = Arrays.copyOf(thunder, thunder.length + 1);
                    thunder[thunder.length - 1] = t;
                }
            }
        }
    }

    public void stepAction() { //放入run方法中执行
        for (int i = 0; i < submarines.length; i++) {
            submarines[i].step();
        }
        for (int i = 0; i < thunder.length; i++) {
            thunder[i].step();
        }
        for (int i = 0; i < bombs.length; i++) {
            bombs[i].step();
        }
    }

    /**
     * 深水炸弹入场的方法,放到按下空格键事件中调用
     */
    public void bombEnterAction() {
        /**
         * 1.战舰对象调用shootBomb方法,接收返回的对象
         * 2.深水数组扩容
         * 3.将对象放入到深水炸弹数组的最后一个位置
         */
        Bomb b = ship.shootBomb();
        bombs = Arrays.copyOf(bombs, bombs.length + 1);
        bombs[bombs.length - 1] = b;
    }

    /**
     * 删除越界海洋对象方法
     * 10毫秒执行一次
     */
    public void outOfBoundsAction() {
        for (int i = 0; i < submarines.length; i++) { //遍历所有的潜艇
            if (submarines[i].isOutOfBounds()) { //越界了
                //将越界潜艇替换为最后一个元素
                submarines[i] = submarines[submarines.length - 1];
                submarines = Arrays.copyOf(submarines, submarines.length - 1); //缩容
            }
        }
        for (int i = 0; i < thunder.length; i++) { //遍历所有的潜艇
            if (thunder[i].isOutOfBounds()) { //越界了
                //将越界潜艇替换为最后一个元素
                thunder[i] = thunder[thunder.length - 1];
                thunder = Arrays.copyOf(thunder, thunder.length - 1); //缩容
            }
        }
        for (int i = 0; i < bombs.length; i++) { //遍历所有的潜艇
            if (bombs[i].isOutOfBounds()) { //越界了
                //将越界潜艇替换为最后一个元素
                bombs[i] = bombs[bombs.length - 1];
                bombs = Arrays.copyOf(bombs, bombs.length - 1); //缩容
            }
        }
    }

    public void bombBangAction() {
        for (int i = 0; i < bombs.length; i++) {
            Bomb b = bombs[i];
            for (int j = 0; j < submarines.length; j++) {
                SeaObject s = submarines[j];
                if (b.isLive() && s.isLive() && s.isHit(b)) {
                    b.goDead();
                    s.goDead();
                    if (s instanceof EnemyScore) {
                        EnemyScore es = (EnemyScore) s;
                        score += es.getScore();
                    }
                    if (s instanceof EnemyLife) {
                        EnemyLife el = (EnemyLife) s;
                        int num = el.getLife();
                        ship.addLift(num);
                    }

                }
            }
        }
    }

    void action() {
        KeyAdapter adapter = new KeyAdapter() { //创建键盘侦听器匿名子类
            @Override
            public void keyPressed(KeyEvent e) { //重写实现按下后要处理的事件逻辑
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { //判断用户按下的按键是否是空格键
                    bombEnterAction();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) { //判断用户按下的按键是否是←键
                    ship.moveLeft(); //调用左移方法
                } else if (e.getKeyCode() == KeyEvent.VK_D) { //判断用户按下的按键是否是→键
                    ship.moveRight(); //调用右移方法
                }
            }
        };
        this.addKeyListener(adapter); //将键盘的侦听的具体子类,添加到侦测组件中

        /*
        1. 具体的任务
        2. 延时多久第一次执行
        3. 执行后距下次执行的间隔时间
        */
        Timer timer = new Timer(); //创建具体的定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                submarineEnterAction(); //调用了潜艇入场的方法
                thunderEnterAction(); //调用了雷入场的方法
                stepAction(); //调用自动移动的方法
                outOfBoundsAction(); //调用删除越界海洋对象的方法
                bombBangAction();
                repaint(); //系统提供的刷新绘制的方法
            }
        };
        timer.schedule(task, 3000, 10);
    }

    /**
     * 绘制窗口的代码
     */
    void paintWorld() {
        JFrame frame = new JFrame(); //创建窗口
        setFocusable(true); //可聚焦的
        frame.add(this); //把面板添加到窗口中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口结束程序
        frame.setSize(WIDTH + 16, HEIGHT + 39); //窗口尺寸
        frame.setLocationRelativeTo(null); //窗口居中呈现
        frame.setVisible(true); //可视化
    }

    //重写父类绘制的方法,实现我们自己项目绘制的逻辑
    @Override
    public void paint(Graphics g) { //g是画笔
        //1.需要一个对象图片
        //ImageIcon s = ImageResources.battleship; //获取战舰图片的资源,存入ship变量中
        //ImageIcon s = ship.getImage();
        //2.绘制的坐标
        //s.paintIcon(null,g,ship.x,ship.y);
        ImageResources.sea.paintIcon(null, g, 0, 0); //背景图片
        ship.paintImage(g);

        for (Bomb bomb : bombs) {
            /*ImageIcon b = bombs[i].getImage();
            b.paintIcon(null,g,bombs[i].x,bombs[i].y);*/
            bomb.paintImage(g);
        }

        for (SeaObject submarine : submarines) {
            /*ImageIcon marine = submarines[i].getImage();
            marine.paintIcon(null,g,submarines[i].x,submarines[i].y);*/
            submarine.paintImage(g);
        }

        for (SeaObject seaObject : thunder) {
            /*ImageIcon t = thunder[i].getImage();
            t.paintIcon(null,g,thunder[i].x,thunder[i].y);*/
            seaObject.paintImage(g);
        }
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("分数:" + score, 200, 50);
        g.drawString("生命:" + ship.getLife(), 400, 50);
    }
}
