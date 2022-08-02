package 项目.danei.src.first.submarine;

import 项目.danei.src.first.submarine.SeaObject;

/**
 * 深水炸弹类
 */
public class Bomb extends SeaObject {
    Bomb(int x, int y) {
        super(x, y,9,12,3);
    }
    @Override
    void step() {
        System.out.println("深水炸弹向下移动");
    }
}
