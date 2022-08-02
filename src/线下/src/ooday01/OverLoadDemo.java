package ooday01;

//重载的演示
public class OverLoadDemo {
    public static void main(String[] args) {
        Aoo o = new Aoo();
        o.show();
        o.show("zhangsan");
        o.show(25);
        o.show("zhangsan", 25);
        o.show(25, "zhangsan");
    }
}











