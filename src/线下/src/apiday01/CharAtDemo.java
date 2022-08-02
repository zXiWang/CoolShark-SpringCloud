package apiday01;

/**
 * char charAt(int index):
 * 返回当前字符串指定位置上的字符----根据位置找字符
 */
public class CharAtDemo {
    public static void main(String[] args) {
        //                      111111---和下面的连起来10/11/12/13/14/15
        //            0123456789012345
        String str = "thinking in java";
        char c = str.charAt(9); //获取str中下标9所对应的字符
        System.out.println(c); //i
    }
}











