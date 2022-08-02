package apiday02;

/**
 * boolean matches(String regex):匹配
 * 使用给定的正则表达式(regex)验证当前字符串的格式是否符合要求，符合则返回true，否则返回false
 */
public class MatchesDemo {
    public static void main(String[] args) {
        /*
          邮箱正则表达式:
            [a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+
          注意: \.中的这个\是正则表达式中的转义符
               \\.中的第1个\，是在转义正则表达式中的\
         */
        String email = "wangkj@tedu.cn";
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
        //使用regex匹配email是否符合格式要求
        boolean match = email.matches(regex);
        if (match) {
            System.out.println("是正确的邮箱格式");
        } else {
            System.out.println("不是正确的邮箱格式");
        }
    }
}



























