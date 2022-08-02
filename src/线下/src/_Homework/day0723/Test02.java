package day0723;

public class Test02 {
    public static void main(String[] args) {
        String str2 = "大家好!";
        str2 = str2 + "我是程序员!";
        System.out.println(str2);
        str2 = str2.replace("程序员", "优秀的程序员");
        System.out.println(str2);
        str2 = str2.replace("优秀的", "牛牛的");
        System.out.println(str2);
        str2 = str2.replace("大家好!", "");
        System.out.println(str2);
    }
}
