//String的演示
public class StringDemo {
    public static void main(String[] args) {
        /*
          使用字面量来创建字符串对象时，JVM会检查常量池中是否有该对象:
          1)若没有，则会创建该字符串对象，并存入常量池中
          2)若有，则直接将常量池中的对象(引用)返回---并不会创建新的字符串对象
         */
        String s1 = "123abc"; //常量池还没有，因此创建该字符串对象，并存入常量池中
        String s2 = "123abc"; //常量池中已经有了，直接复用对象
        String s3 = "123abc"; //常量池中已经有了，直接复用对象
        //引用类型==，比较的是地址是否相同-----这是规定
        System.out.println(s1==s2); //true
        System.out.println(s1==s3); //true
        System.out.println(s2==s3); //true

        s1 = s1 + "!"; //创建新的字符串对象(123abc!)并将地址赋值给s1
        System.out.println(s1==s2); //false

    }
}


























