package 项目.danei.src.first.submarine;

import 项目.danei.src.first.submarine.SeaObject;

/**
 * 水雷类
 */
public class Mine extends SeaObject {

    Mine(int x,int y) { //通过外部获取对象的x坐标和y坐标
        super(x,y,11,11,2);
    }

    @Override
    void step() {
        System.out.println("水雷向上移动");
    }
}
