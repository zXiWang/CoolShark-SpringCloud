package submarine;

import javax.swing.*;
/*
注意:点项目右键New一个Directory，起名img，将8张图片粘贴进来
 */
/** 图片类 */
public class Images {
    public static ImageIcon sea;         //海洋图片
    public static ImageIcon gameover;    //游戏结束图片
    public static ImageIcon battleship;  //战舰图片
    public static ImageIcon obsersubm;   //侦察潜艇图片
    public static ImageIcon torpesubm;   //鱼雷潜艇图片
    public static ImageIcon minesubm;    //水雷潜艇图片
    public static ImageIcon mine;        //水雷图片
    public static ImageIcon bomb;        //炸弹图片

    static{ //初始化静态图片
        //将img中的sea.png读取到静态变量sea中
        sea = new ImageIcon("img/sea.png");
        gameover = new ImageIcon("img/gameover.png");
        battleship = new ImageIcon("img/battleship.png");
        obsersubm = new ImageIcon("img/obsersubm.png");
        torpesubm = new ImageIcon("img/torpesubm.png");
        minesubm = new ImageIcon("img/minesubm.png");
        mine = new ImageIcon("img/mine.png");
        bomb = new ImageIcon("img/bomb.png");
    }

    //测试图片是否读取成功
    public static void main(String[] args) {
        //返回8表示图片读取成功，返回其余数字表示图片读取失败
        System.out.println(sea.getImageLoadStatus()); //8
        System.out.println(gameover.getImageLoadStatus());
        System.out.println(battleship.getImageLoadStatus());
        System.out.println(obsersubm.getImageLoadStatus());
        System.out.println(torpesubm.getImageLoadStatus());
        System.out.println(minesubm.getImageLoadStatus());
        System.out.println(mine.getImageLoadStatus());
        System.out.println(bomb.getImageLoadStatus());
    }

}



























