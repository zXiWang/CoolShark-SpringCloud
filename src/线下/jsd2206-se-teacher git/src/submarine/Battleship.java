package submarine;

import javax.swing.*;

/** 战舰 */
public class Battleship extends SeaObject {
    private int life; //命数
    /** 构造方法 */
    public Battleship(){
        super(66,26,270,124,20);
        life = 1;
    }

    /** 重写move()移动 */
    public void move(){
        //暂时搁置
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        return Images.battleship; //返回战舰图片
    }

    /** 发射炸弹---生成炸弹对象 */
    public Bomb shootBomb(){
        return new Bomb(this.x,this.y); //炸弹的初始坐标就是战舰的坐标
    }

    /** 战舰左移 */
    public void moveLeft(){
        x -= speed; //x-(向左)
    }
    /** 战舰右移 */
    public void moveRight(){
        x += speed; //x+(向右)
    }

    /** 战舰增命 */
    public void addLife(int num){
        life += num; //命数增num
    }

    /** 获取命数 */
    public int getLife(){
        return life; //返回命数
    }

    /** 战舰减命 */
    public void subtractLife(){
        life--; //命数减1
    }
}

















