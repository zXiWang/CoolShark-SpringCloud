package apiday01;

/**
 * String toUpperCase():
 * 将当前字符串中的英文部分给转换为全大写
 * String toLowerCase():
 * 将当前字符串中的英文部分给转换为全小写
 */
public class ToUpperCaseDemo {
    public static void main(String[] args) {
        String str = "我爱Java!";
        String upper = str.toUpperCase(); //将str中英文部分转换为全大写，存到upper中
        System.out.println(upper); //我爱JAVA!

        String lower = str.toLowerCase(); //将str中英文部分转换为全小写，存到lower中
        System.out.println(lower); //我爱java!
    }
}








