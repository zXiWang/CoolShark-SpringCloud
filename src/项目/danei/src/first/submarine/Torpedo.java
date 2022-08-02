package 项目.danei.src.first.submarine;

import 项目.danei.src.first.submarine.SeaObject;

/**
 * 鱼雷类
 */
public class Torpedo extends SeaObject {

    Torpedo(int x,int y) {
        super(x,y,5,18,2);
    }

    @Override
    void step() {
        System.out.println("鱼雷向上移动");
    }
}
