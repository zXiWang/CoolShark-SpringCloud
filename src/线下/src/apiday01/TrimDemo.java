package apiday01;

/**
 * String trim():
 * 去除当前字符串两边的空白字符
 */
public class TrimDemo {
    public static void main(String[] args) {
        String str = "     hello world     ";
        System.out.println(str); //     hello world
        str = str.trim(); //去除str两边的空白字符，并将去除之后的新的对象存储到str中
        System.out.println(str); //hello world
    }
}










