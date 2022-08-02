package 项目.danei.src.first.submarine;

import 项目.danei.src.first.submarine.SeaObject;

/**
 * 水雷潜艇类
 */
public class MineSubmarine extends SeaObject {

    MineSubmarine() {
        super(63,19);
    }

    @Override
    void step() {
        System.out.println("水雷潜艇向右移动");
    }
}
