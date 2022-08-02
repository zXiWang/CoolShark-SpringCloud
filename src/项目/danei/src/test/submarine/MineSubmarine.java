package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 水雷潜艇类
 */
public class MineSubmarine extends SeaObject implements EnemyLife {

    MineSubmarine() {
        super(63,19);
    }

    @Override
    void step() {
        x += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前水雷潜艇对象是活着的状态
            return ImageResources.minesubm; //返回水雷潜艇图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
    }

    @Override
    public int getLife() {
        return 1; //重写,打掉水雷潜艇,战舰得一条命
    }
}
