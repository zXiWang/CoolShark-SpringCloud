package apiday02;

public class Test {
    public static void main(String[] args) {
        String path = "http://localhost:8088/myweb/reg?name=zhangsan&pwd=123456&nick=san&age=16";
        String[] str = path.split("\\?");
        str = str[1].split("&");
        for (int i = 0; i < str.length; i++) {
            String[] temp = str[i].split("=");
            System.out.println("参数名:" + temp[0] + ",参数值:" + temp[1]);

        }
    }
}
