package 项目.danei.src.test.submarine;

import javax.swing.*;

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

    public void moveLeft() { //向左移动
        this.x -= speed;
    }
    public void moveRight() { //向右移动
        this.x += speed;
    }

    @Override //战舰比较特殊,不是一被攻击就死亡,如果战舰死亡则意味着游戏结束,所以不需要判断状态
    public ImageIcon getImage() {
        return ImageResources.battleship;
    }

    /**
     * 生成深水炸弹对象的方法
     */
    public Bomb shootBomb() {
        return new Bomb(this.x,this.y); //返回深水炸弹对象,坐标则是当前战舰对象的坐标
    }

    public void addLift(int num){
        life+=num;
    }
    public int getLife(){
        return life;
    }
}
