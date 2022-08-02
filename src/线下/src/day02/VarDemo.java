package day02;

//变量的演示
public class VarDemo {
    public static void main(String[] args) {
        //1)变量的声明:------开帐户
        int a; //声明一个整型的变量，名为a
        int b, c, d; //声明三个整型的变量，名为b,c,d
        //int a; //编译错误，变量不能同名

        //2)变量的初始化:-----给帐户存钱
        int e = 250; //声明整型变量e并赋值为250
        int f;   //声明整型变量f
        f = 250; //给变量f赋值为250
        f = 360; //修改变量f的值为360

        //3)变量的使用:------使用的是帐户里面的钱
        int g = 5;
        int h = g + 10; //取出g的值5，加10后，再赋值给变量h
        System.out.println(h);   //输出变量h的值15
        System.out.println("h"); //输出h，双引号中的原样输出
        g = g + 10; //在g本身基础之上增10
        System.out.println(g); //15

        //System.out.println(i); //编译错误，变量i未声明
        int i;
        //System.out.println(i); //编译错误，变量i未初始化

        //4)变量的命名:
        int a1, a_5$, _3c, $_5;
        //int a*b; //编译错误，不能包含*号等特殊符号
        //int 1a; //编译错误，不能以数字开头
        int aa = 5;
        //System.out.println(aA); //编译错误，严格区分大小写
        //int class; //编译错误，不能使用关键字

        //int j;  //不直观，不建议
        //int 年龄; //允许中文，但不建议
        //int nianLing; //必须杜绝，既不直观也不专业，不建议
        int age; //建议"英文的见名知意"
        int score, myScore, myJavaScore; //建议"小驼峰命名法"

    }
}

















