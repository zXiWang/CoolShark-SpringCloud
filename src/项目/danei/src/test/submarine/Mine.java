package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 水雷类
 */
public class Mine extends SeaObject {

    Mine(int x,int y) { //通过外部获取对象的x坐标和y坐标
        super(x,y,11,11,2);
    }

    @Override
    void step() {
        y -= speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前水雷对象是活着的状态
            return ImageResources.mine; //返回水雷图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
    }
    /**
     * 检测水雷是否越界
     * 如果越界则返回true,没有越界则返回false
     */
    public boolean isOutOfBounds() {
        return this.y <= 150 - this.height; //潜艇的x>=窗口的宽,即越界了
    }
}
