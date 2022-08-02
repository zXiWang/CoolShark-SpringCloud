package 项目.danei.src.first.day04;

/**
 * 自增自减运算符的使用测试类
 */
public class OperationDemo02 {
    public static void main(String[] args) {
        //1.单独运算
        //符号在前后都一样,正常做自增自减
        int a = 5;
        a++; //在a的数据上+1,等价于 a = a + 1;
        System.out.println(a);
        int b = 5;
        b--;
        System.out.println(b);

        /**
         * 符号在前,就先执行符号对应的自增或自减,然后再参与运算
         * 符号在后,就先参与其他运算,再去执行符号对应的自增或自减
         */
        int c = 5;
        int d = 5;
        int e = c++;
        int f = ++d;
        System.out.println(c); //6
        System.out.println(d); //6
        System.out.println(e); //5
        System.out.println(f); //6

        int g = 5;
        int h = 5;
        int i = g--;
        int j = --h;
        System.out.println(g); //4
        System.out.println(h); //4
        System.out.println(i); //5
        System.out.println(j); //4
    }
}
