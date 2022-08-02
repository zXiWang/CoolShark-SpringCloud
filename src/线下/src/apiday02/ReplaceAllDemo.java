package apiday02;

/**
 * String replaceAll(String regex,String s):替换
 * 将当前字符串中满足正则表达式(regex)的部分给替换为给定的字符串(s)
 */
public class ReplaceAllDemo {
    public static void main(String[] args) {
        String line = "abc123def456ghi78";
        line = line.replaceAll("[0-9]+", "#NUMBER#");
        System.out.println(line);
    }
}



















