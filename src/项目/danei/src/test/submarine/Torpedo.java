package 项目.danei.src.test.submarine;

import javax.swing.*;

/**
 * 鱼雷类
 */
public class Torpedo extends SeaObject {

    Torpedo(int x, int y) {
        super(x, y, 5, 18, 2);
    }

    @Override
    void step() {
        y -= speed;
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) { //如果当前鱼雷对象是活着的状态
            return ImageResources.torpedo; //返回鱼雷图片
        }
        return null; //如果执行到这一步代码,则意味着当前对象为死亡状态
    }

    /**
     * 检测鱼雷是否越界
     * 如果越界则返回true,没有越界则返回false
     */
    public boolean isOutOfBounds() {
        return this.y <= -this.height; //鱼雷的y<=-鱼雷的高,即越界了
    }
}
