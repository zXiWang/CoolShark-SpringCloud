package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 侦查潜艇类
 */
public class ObserveSubmarine extends SeaObject implements EnemyScore {

    ObserveSubmarine() {
        super(63,19);
    }

    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前侦查潜艇对象是活着的状态
            return ImageResources.obsersubm; //返回侦查潜艇图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
    }

    @Override
    public int getScore() {
        return 10; //重写,打掉侦查潜艇,玩家得10分
    }
}
