package 项目.danei.src.first.day05;

import java.util.Scanner;

/**
 * switch case测试类
 */
public class SwitchCaseDemo {
    public static void main(String[] args) {
        /**
         * switch case语法结构:
         *      switch(需要判断的数据) {
         *          case 1:
         *              //满足匹配1,所执行的代码块
         *              break;
         *          case 2:
         *              //满足匹配2,所执行的代码块
         *              break;
         *          case 3:
         *              //满足匹配3,所执行的代码块
         *              break;
         *      }
         */
        System.out.println("宽带业务请按1,话费业务请按2,人工服务请按0~");
        int command = new Scanner(System.in).nextInt(); //获取用户输入的指令
        switch (command) { //需要判断的数据
            case 1: //判断command 是否与 1 匹配
                System.out.println("请输入您的身份证号!");
                break; //遇到break,跳出此程序
            case 2:
                System.out.println("请输入您的手机号!");
                break;
            case 0:
                System.out.println("座机服务繁忙,请稍后再拨!");
                break;
            default: //默认的,若能执行到这里,则表示上述都没有匹配上
                System.out.println("您输入的指令有误,按#号键返回上一层!");
        }
        System.out.println("代码继续向后执行!~");
    }
}
