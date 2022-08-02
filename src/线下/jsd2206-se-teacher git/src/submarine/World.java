package submarine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** 整个窗口世界 */
public class World extends JPanel { //2.
    public static final int WIDTH = 641;  //窗口的宽
    public static final int HEIGHT = 479; //窗口的高

    public static final int RUNNING = 0;   //运行状态
    public static final int PAUSE = 1;     //暂停状态
    public static final int GAME_OVER = 2; //游戏结束状态
    private int state = RUNNING; //当前状态(默认运行状态)

    //如下这一堆对象就是窗口中你所看到的对象了
    private Battleship ship = new Battleship(); //战舰对象
    private SeaObject[] submarines = {}; //潜艇数组
    private Mine[] mines = {}; //水雷数组
    private Bomb[] bombs = {}; //炸弹数组

    /** 生成潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)对象 */
    private SeaObject nextSubmarine(){
        Random rand = new Random(); //随机数对象
        int type = rand.nextInt(20); //0到19之间的随机数
        if(type<10){ //0到9时，返回侦察潜艇对象
            return new ObserveSubmarine();
        }else if(type<16){ //10到15时，返回鱼雷潜艇对象
            return new TorpedoSubmarine();
        }else{ //16到19时，返回水雷潜艇对象
            return new MineSubmarine();
        }
    }

    private int subEnterIndex = 0; //潜艇入场计数
    /** 潜艇(侦察潜艇、鱼雷潜艇、水雷入场)入场 */
    private void submarineEnterAction(){ //每10毫秒走一次
        subEnterIndex++; //每10毫秒增1
        if(subEnterIndex%40==0){ //每400(40*10)毫秒走一次
            SeaObject obj = nextSubmarine(); //获取潜艇对象
            submarines = Arrays.copyOf(submarines,submarines.length+1); //扩容
            submarines[submarines.length-1] = obj; //将obj添加到submarines的最后一个元素上
        }
    }

    private int mineEnterIndex = 0; //水雷入场计数
    /** 水雷入场 */
    private void mineEnterAction(){ //每10毫秒走一次
        mineEnterIndex++; //每10毫秒增1
        if(mineEnterIndex%100==0) { //每1000毫秒走一次
            for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
                if(submarines[i] instanceof MineSubmarine){ //若潜艇为水雷潜艇
                    MineSubmarine ms = (MineSubmarine)submarines[i]; //将潜艇转换为水雷潜艇类型
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
        for(int i=0;i<bombs.length;i++){ //遍历所有炸弹
            bombs[i].move(); //炸弹移动
        }
    }

    /** 删除越界的海洋对象----避免内存泄漏 */
    private void outOfBoundsAction(){ //每10毫秒走一次
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            if(submarines[i].isOutOfBounds() || submarines[i].isDead()){ //若出界了，或者死了的
                submarines[i] = submarines[submarines.length-1]; //将越界元素替换为最后一个元素
                submarines = Arrays.copyOf(submarines,submarines.length-1); //缩容
            }
        }

        for(int i=0;i<mines.length;i++){ //遍历所有水雷
            if(mines[i].isOutOfBounds() || mines[i].isDead()){ //若出界了，或者死了的
                mines[i] = mines[mines.length-1]; //将越界元素替换为最后一个元素
                mines = Arrays.copyOf(mines,mines.length-1); //缩容
            }
        }

        for(int i=0;i<bombs.length;i++){ //遍历所有炸弹
            if(bombs[i].isOutOfBounds() || bombs[i].isDead()){ //若出界了，或者死了的
                bombs[i] = bombs[bombs.length-1]; //将越界元素替换为最后一个元素
                bombs = Arrays.copyOf(bombs,bombs.length-1); //缩容
            }
        }
    }

    private int score = 0 ; //玩家得分
    /** 炸弹与潜艇的碰撞 */
    private void bombBangAction(){ //每10毫秒走一次
        for(int i=0;i<bombs.length;i++){ //遍历所有炸弹
            Bomb b = bombs[i]; //获取每一个炸弹
            for(int j=0;j<submarines.length;j++){ //遍历所有潜艇
                SeaObject s = submarines[j]; //获取每一个潜艇
                if(b.isLive() && s.isLive() && s.isHit(b)){ //若都活着并且还撞上了
                    s.goDead(); //潜艇去死
                    b.goDead(); //炸弹去死
                    if(s instanceof EnemyScore){ //若被撞潜艇为分
                        EnemyScore es = (EnemyScore)s; //将被撞潜艇强转为得分接口
                        score += es.getScore(); //玩家得分
                    }
                    if(s instanceof EnemyLife){ //若被撞潜艇为命
                        EnemyLife el = (EnemyLife)s; //将被撞潜艇强转为得命接口
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
            Mine m = mines[i]; //获取每一个水雷
            if(m.isLive() && ship.isLive() && m.isHit(ship)){ //若都活着并且还撞上了
                m.goDead(); //水雷去死
                ship.subtractLife(); //战舰减命
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
        //游戏一开始，先询问是否读取存档
        File file = new File("game.sav");//java.io.File
        if(file.exists()){//判断存档文件是否存在?
            //若存档文件存在，则询问用户是否读取存档
            int r = JOptionPane.showConfirmDialog(
            World.this, "是否读取存档"
            );
            if(r == JOptionPane.YES_OPTION){//如果用户点击的为"是"这个按钮
                //读取存档
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    GameInfo gameInfo = (GameInfo) ois.readObject();
                    ship = gameInfo.getShip();
                    submarines = gameInfo.getSubmarines();
                    mines = gameInfo.getMines();
                    bombs = gameInfo.getBombs();
                    subEnterIndex = gameInfo.getSubEnterIndex();
                    mineEnterIndex = gameInfo.getMineEnterIndex();
                    score = gameInfo.getScore();

                    ois.close();
                }catch(Exception e){}
            }
        }


        //键盘侦听器---不要求掌握
        KeyAdapter k = new KeyAdapter() {
            /** 重写keyReleased按键抬起事件 keyPressed()键盘按下事件 */
            public void keyReleased(KeyEvent e) { //当按键抬起时会自动触发---不要求掌握
                if(e.getKeyCode()==KeyEvent.VK_P){ //若抬起的是P键---不要求掌握
                    state = PAUSE;//将游戏暂停

                    //方法返回值表示用户在弹出窗口上点击的是哪个按钮
                    int r = JOptionPane.showConfirmDialog(//在当前游戏窗口前弹出一个确认框---不要求掌握
                            World.this, //参数1:确认框在游戏窗口上弹出
                            "保存游戏吗?" //参数2:确认框上的提示文字
                    );
                    //判断用户点击的是"是"这个按钮我们才进行存档
                    if(r == JOptionPane.YES_OPTION){
                        //将当前World中各项信息都传入到GameInfo中
                        GameInfo gameInfo = new GameInfo(
                                ship, submarines, mines, bombs,
                                subEnterIndex, mineEnterIndex, score
                        );
                        try {
                            FileOutputStream fos = new FileOutputStream("game.sav");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(gameInfo);//将当前游戏所有数据写入文件保存
                            oos.close();
                        }catch(Exception ex){}

                    }


                    state = RUNNING;//当确认窗口消失后，让游戏恢复运行

                }

                if(state==RUNNING){ //仅在运行状态下执行
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){ //若抬起的是空格键---不要求掌握
                        Bomb obj = ship.shootBomb(); //获取炸弹对象
                        bombs = Arrays.copyOf(bombs,bombs.length+1); //扩容
                        bombs[bombs.length-1] = obj; //将obj添加到最后一个元素上
                    }
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){ //若抬起的是左箭头---不要求掌握
                        ship.moveLeft(); //战舰左移
                    }
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT) { //若抬起的是右箭头---不要求掌握
                        ship.moveRight(); //战舰右移
                    }
                }
            }
        };
        this.addKeyListener(k); //添加侦听---不要求掌握

        Timer timer = new Timer(); //定时器对象
        int interval = 10; //定时间隔(以毫秒为单位)
        timer.schedule(new TimerTask() {
            public void run() { //定时干的事---每10毫秒自动调用
                if(state==RUNNING){ //仅在运行状态时执行
                    submarineEnterAction(); //潜艇(侦察潜艇、鱼雷潜艇、水雷入场)入场
                    mineEnterAction();      //水雷入场
                    moveAction();           //海洋对象移动
                    outOfBoundsAction();    //删除越界的海洋对象
                    bombBangAction();       //炸弹与潜艇的碰撞
                    mineBangAction();       //水雷与战舰的碰撞
                    checkGameOverAction();  //检测游戏结束
                    repaint(); //重画---系统自动调用paint()方法
                }
            }
        }, interval, interval); //定时日程表
    }

    //集团这边网络出问题了，有些中心连不上了，大家稍等片刻

    /** 重写paint()画  g:系统自带的画笔 */
    public void paint(Graphics g){ //每10毫秒走一次
        Images.sea.paintIcon(null,g,0,0); //画海洋图---不要求掌握
        ship.paintImage(g); //画战舰
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            submarines[i].paintImage(g); //画潜艇
        }
        for(int i=0;i<mines.length;i++){ //遍历所有水雷
            mines[i].paintImage(g); //画水雷
        }
        for(int i=0;i<bombs.length;i++){ //遍历所有炸弹
            bombs[i].paintImage(g); //画炸弹
        }
        g.drawString("SCORE: "+score,200,50); //画分---不要求掌握
        g.drawString("LIFE: "+ship.getLife(),400,50); //画命---不要求掌握

        if(state==GAME_OVER){ //若当前为游戏结束状态
            Images.gameover.paintIcon(null,g,0,0); //画海洋图---不要求掌握
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(); //3.
        World world = new World(); //会创建窗口中的那一堆对象
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH+16, HEIGHT+39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //系统自动调用paint()方法

        world.action(); //启动程序的执行
    }
}




















