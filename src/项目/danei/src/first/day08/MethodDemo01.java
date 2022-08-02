package 项目.danei.src.first.day08;

/**
 * 无参无返回值方法的测试类
 */
public class MethodDemo01 {
    public static void main(String[] args) {
        //现象 : 代码冗余,多次定义重复的程序
        /*System.out.println("鹅肝");
        System.out.println("波龙");
        System.out.println("三文鱼");
        System.out.println("甜虾");
        System.out.println("烤鳗鱼");

        System.out.println("鹅肝");
        System.out.println("波龙");
        System.out.println("三文鱼");
        System.out.println("甜虾");
        System.out.println("烤鳗鱼");*/
        menu(); //调用menu方法
        menu();
        menu();
    }

    /**
     * 在类的内部,其他方法的外部定义一个方法
     * 做一个菜单 ---> 无参无返回值的方法
     * 返回值类型 方法名() {}
     * static 后续会讲,目前使用是能够保证menu方法在main方法中被调用
     */
    static void menu() { //一次定义,多次使用,并且便于修改
        System.out.println("鹅肝");
        System.out.println("波龙");
        System.out.println("三文鱼");
        System.out.println("甜虾");
        System.out.println("烤鳗鱼");
        System.out.println("烤生蚝");
    }
}
