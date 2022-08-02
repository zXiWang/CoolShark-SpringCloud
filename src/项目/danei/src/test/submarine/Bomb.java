package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 深水炸弹类
 */
public class Bomb extends SeaObject {
    Bomb(int x, int y) {
        super(x, y,9,12,3);
    }
    @Override
    void step() {
        y += speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前深水炸弹对象是活着的状态
            return ImageResources.bomb; //返回深水炸弹图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
        //return this.isLive() ? ImageResources.bomb : null;
    }
    /**
     * 检测炸弹是否越界
     * 如果越界则返回true,没有越界则返回false
     */
    public boolean isOutOfBounds() {
        return this.y >= GameWorld.HEIGHT; //炸弹的y>=窗口的高,即越界了
    }
}
