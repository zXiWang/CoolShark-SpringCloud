package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 图片资源类 : 主要用来加载项目中所需要的图片资源
 */
public class ImageResources {
    //ImageIcon 用来存图片类型
    public static ImageIcon battleship; //用于战舰图片的静态变量
    public static ImageIcon bomb; //用于深水炸弹图片的静态变量
    public static ImageIcon gameover; //用于游戏结束图片的静态变量
    public static ImageIcon mine; //用于水雷图片的静态变量
    public static ImageIcon minesubm; //用于水雷潜艇图片的静态变量
    public static ImageIcon obsersubm; //用于侦查潜艇图片的静态变量
    public static ImageIcon sea; //用于海洋背景图片的静态变量
    public static ImageIcon start; //用于游戏开始图片的静态变量
    public static ImageIcon torpedo; //用于鱼雷图片的静态变量
    public static ImageIcon torpesubm; //用于鱼雷潜艇图片的静态变量

    static { //静态代码块,用于为上面静态变量赋值具体的图片(路径)
        battleship = new ImageIcon("img/battleship.png");
        bomb = new ImageIcon("img/bomb.png");
        gameover = new ImageIcon("img/gameover.png");
        mine = new ImageIcon("img/mine.png");
        minesubm = new ImageIcon("img/minesubm.png");
        obsersubm = new ImageIcon("img/obsersubm.png");
        sea = new ImageIcon("img/sea.png");
        start = new ImageIcon("img/start.png");
        torpedo = new ImageIcon("img/torpedo.png");
        torpesubm = new ImageIcon("img/torpesubm.png");
    }
}
