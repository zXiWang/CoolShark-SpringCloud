package 线下.src.ooday05;
//static的演示
public class StaticDemo {
    public static void main(String[] args) {
        Loo o1 = new Loo();
        o1.show();
        Loo o2 = new Loo();
        o2.show();
        Loo o3 = new Loo();
        o3.show();
        System.out.println(Loo.b); //常常通过类名点来访问

        Noo.plus(5,7); //常常通过类名点来访问

        Poo o4 = new Poo();
        Poo o5 = new Poo();
    }
}

//演示静态块
class Poo{
    static{
        System.out.println("静态块");
    }
    Poo(){
        System.out.println("构造方法");
    }
}









//演示静态方法的应用场景
class Noo{
    int a; //实例变量---描述对象的属性
    //show()方法中需要访问对象的属性a，说明show()的操作与对象有关---不能静态方法
    void show(){
        System.out.println(a);
    }
    //plus()方法中不需要访问对象的属性和行为，说明plus()的操作与对象无关-可以静态方法
    static void plus(int num1,int num2){
        int num=num1+num2;
        System.out.println(num);
    }
}











//演示静态方法
class Moo{
    int a; //实例变量(对象来访问)
    static int b; //静态变量(类名来访问)
    void show(){ //有隐式this
        System.out.println(this.a);
        System.out.println(Moo.b);
    }
    static void test(){ //没有隐式this
        //静态方法中没有隐式this传递
        //没有this就意味着没有对象
        //而实例成员a必须通过对象来访问
        //所以如下语句会发生编译错误
        //System.out.println(a); //编译错误
        System.out.println(Moo.b);
    }
}











//演示静态变量
class Loo{
    int a;
    static int b;
    Loo(){
        a++;
        b++;
    }
    void show(){
        System.out.println("a="+a+",b="+b);
    }
}















