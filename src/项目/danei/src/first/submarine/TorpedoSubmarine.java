package 项目.danei.src.first.submarine;

import 项目.danei.src.first.submarine.SeaObject;

/**
 * 鱼雷潜艇类
 */
public class TorpedoSubmarine extends SeaObject {

    TorpedoSubmarine() {
        super(64,20);
    }

    @Override
    void step() {
        System.out.println("鱼雷潜艇向右移动");
    }
}
