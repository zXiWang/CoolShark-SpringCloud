package apiday01;

/**
 * String substring(int start,int end):
 * 截取当前字符串中指定范围内的字符串(含头不含尾--包含start，但不包含end)
 */
public class SubstringDemo {
    public static void main(String[] args) {
        //                      1
        //            01234567890
        String str = "www.tedu.cn";
        String name = str.substring(4, 8);
        System.out.println(name); //tedu

        name = str.substring(4); //从下标4开始一直到末尾
        System.out.println(name); //tedu.cn
    }
}
