package 项目.danei.src.first.day06;

/**
 * 双重for循环的使用测试类
 */
public class ForDemo2 {
    public static void main(String[] args) {
        //int num = 9;
        //外层循环走一次,内层循环走多次
        for (int num = 1; num <= 9; num++) { //外层循环
            for (int i = 1; i <= num; i++) { //内层循环
                System.out.print(i +"*"+ num +"="+ (i * num) + "\t"); //tab键
            }
            System.out.println(); //换行
        }

        //通过双重for循环,绘制图形
        for (int i = 1; i <= 5; i++) { //控制行数
            for (int j = 1; j <=i; j++) { //控制列数
                System.out.print("*");
            }
            System.out.println();
        }

    }
}
