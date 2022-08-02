package exception;

/**
 * 异常常用方法
 */
public class ExceptionApiDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了");

        try {
            String str = "abc";
            System.out.println(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            System.out.println("出错了");
            e.printStackTrace();
            String message = e.getMessage();//获取错误消息
            System.out.println(message);
        }
        System.out.println("程序结束了");
    }
}
