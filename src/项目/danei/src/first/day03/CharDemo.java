package 项目.danei.src.first.day03;

/**
 * 字符类型的使用测试类
 */
public class CharDemo {
    public static void main(String[] args) {
        char temp = 'a'; //声明一个char类型的变量,名为temp,里面存储了字符a
        System.out.println(temp);
        System.out.println((int)temp); //将temp中的字符转化成码
        char t2 = 97; //很少使用此方式
        System.out.println(t2);
//        char temp1 = '我';
//        System.out.println(temp1);
//        System.out.println((int)temp1);

        char t3 = '\''; //存储特殊符号需要用\来进行转义
        System.out.println(t3);
        char t4 = '\\';
        System.out.println(t4);

        //---------------boolean----------------
        boolean result = true;
        boolean result2 = false;
        System.out.println(result);
        System.out.println(result2);
    }
}
