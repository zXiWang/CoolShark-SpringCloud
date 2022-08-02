package 项目.danei.src.first.submarine;

/**
 * 侦查潜艇类
 */
public class ObserveSubmarine extends SeaObject {

    ObserveSubmarine() {
        super(63,19);
    }

    @Override
    void step() {
        System.out.println("侦查潜艇向右移动");
    }
}
