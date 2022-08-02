package 项目.danei.src.first.day03;

/**
 * long类型使用的测试类
 */
public class LongDemo {
    public static void main(String[] args) {
        //1.长整数直接量后缀需要加上L
        long a = 10000000000L;
        //2.计算数据的结果跟L存放位置有关
        long b = 1000000000 * 2 * 10L;
        System.out.println(b); //200亿
        //30亿已经超出int存值范围
        long c = 1000000000L * 3L * 10L;
        System.out.println(c);
    }
}
