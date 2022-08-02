package apiday01;

//String的演示
public class StringDemo {
    public static void main(String[] args) {
        /*
          常见面试题:
            String s = new String("hello");
            问:如上语句创建了几个对象?
            答:2个
              第一个:字面量"hello"
              ----java会创建一个String对象表示字面量"hello"，并将其存入常量池
              第二个:new String()
              ----new String()时会再创建一个字符串对象，并引用hello字符串的内容
         */
        String s = new String("hello"); //创建2个对象
        String s1 = "hello"; //复用常量池中的字面量对象
        System.out.println("s:" + s);   //s:hello
        System.out.println("s1:" + s1); //s1:hello
        System.out.println(s == s1); //false，==比较的是地址是否相同

        //在实际应用中，String比较相等一般都是比较字符串内容是否相等
        //因此我们需要使用equals()方法来比较两个字符串的内容是否相同
        System.out.println(s.equals(s1)); //true，equals()比较的是内容是否相同

        /*
        String s1 = "123abc"; //堆中有一个123abc字面量对象，同时缓存到常量池中
        //编译器在编译时，若发现是两个字面量相连，则会直接连接好并将结果保存起来
        //如下语句相当于: String s2 = "123abc";
        String s2 = "123"+"abc"; //直接复用常量池中的对象
        System.out.println(s1==s2); //true

        String s3 = "123";
        //因为s3是一个变量，所以在编译期并不会直接连接好
        String s4 = s3+"abc"; //创建一个新的对象存储123abc
        System.out.println(s4==s1); //false
        */
    }
}
