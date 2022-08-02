package day06;

//方法的演示
public class MethodDemo {
    public static void main(String[] args) {
        //say(); //调用say()方法

        /*
        //sayHi(); //编译错误，有参则必须传参
        //sayHi(250); //编译错误，参数类型不匹配
        sayHi("zhangsan"); //String name="zhangsan" ------实参
        sayHi("lisi"); //String name="lisi" --------------实参
         */

        /*
        sayHello("WKJ",18); //String name="WKJ",int age=18 ----实参
        sayHello("zhangsan",25); -------------实参
         */

        /*
        double a = getNum(); //getNum()的值就是return后的那个数
        System.out.println(a); //8.88---模拟对返回值的后续操作
         */

        /*
        int b = plus(5,6);
        System.out.println(b); //11---模拟对返回值的后续操作

        int m=5,n=6;
        int c = plus(m,n); //传的是m和n里面的数
        System.out.println(c); //11---模拟对返回值的后续操作
         */

        /*
        int[] d = testArray();
        System.out.println("长度为:"+d.length); //10---模拟对返回值的后续操作
        for(int i=0;i<d.length;i++){ //---模拟对返回值的后续操作
            System.out.println(d[i]);
        }
         */

        //sayHello("zhangsan",25);
        //sayHello("lisi",66);

        a(); //111，333，222，444
        System.out.println(444);
    }

    public static void a() {
        System.out.println(111);
        b(); //方法的嵌套调用
        System.out.println(222);
    }

    public static void b() {
        System.out.println(333);
    }


    //有参无返回值
    public static void sayHello(String name, int age) { //形参
        if (age >= 50) { //在某种特定条件下，提前结束方法
            return; //结束方法
        }
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁了");
    }


    //无参有返回值
    public static int[] testArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    //有参有返回值
    public static int plus(int num1, int num2) {
        int num = num1 + num2;
        return num; //返回的是num里面的那个数
        //return num1+num2; //返回的是num1与num2的和
    }

    //无参有返回值
    public static double getNum() {
        /*
          java规定:
          1)有返回值的方法中，必须得通过return来返回数据
          2)并且return后数据的类型，必须与返回值类型匹配
         */
        //return "abc"; //编译错误，返回值类型必须匹配
        return 8.88; //1)结束方法的执行  2)返回结果给调用方
    }


    //无参无返回值
    public static void say() {
        System.out.println("大家好，我叫WKJ，今年38岁了");
    }

    //有参无返回值
    public static void sayHi(String name) { //形参
        System.out.println("大家好，我叫" + name + "，今年38岁了");
    }


}

















