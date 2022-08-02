package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 鱼雷潜艇类
 */
public class TorpedoSubmarine extends SeaObject implements EnemyScore {

    TorpedoSubmarine() {
        super(64,20);
    }

    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前鱼雷潜艇对象是活着的状态
            return ImageResources.torpesubm; //返回鱼雷潜艇图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
    }

    @Override
    public int getScore() {
        return 40; //重写,打掉鱼雷潜艇,玩家得40分
    }
}
