package 线下.src.ooday05;
//final的演示
public class FinalDemo {
    private  abc="123";
    public static void main(String[] args) {
        System.out.println(abc);
    }
}

//演示final修饰类
final class Hoo{}
//class Ioo extends Hoo{} //编译错误，final的类不能被继承
class Joo{}
final class Koo extends Joo{} //正确，不能当老爸，但能当儿子


//演示final修饰方法
class Foo{
    void show(){}
    final void test(){}
}
class Goo extends Foo{
    void show(){}
    //void test(){} //编译错误，final修饰的方法，不能被重写
}

//演示final修饰变量
class Eoo{
    int a = 5;
    final int b = 5;
    void show(){
        a = 55;
        //b = 55; //编译错误，final修饰的变量，不能被改变
    }
}


















