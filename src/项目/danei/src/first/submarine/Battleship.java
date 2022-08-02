package 项目.danei.src.first.submarine;

/**
 * 战舰类
 */
public class Battleship extends SeaObject {
    int life; //生命值

    //添加构造方法 ---> 有参还是无参?数据能不能写死?!
    Battleship() {
        super(270,124,66,26,20);
        life = 5;
    }

    @Override
    void step() {
        System.out.println("战舰通过键盘左右移动");
    }
}
