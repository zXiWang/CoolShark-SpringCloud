package apiday02;

//Object的演示
public class ObjectDemo {
    public static void main(String[] args) {
        /*
          调用Object类的equals()，内部还是在使用==比较地址，没有实际意义
          若想比较对象的属性值是否相同，我们认为Object的equals()并不能满足需求
          因此常常需要重写equals()
        */
        /*
        Point p1 = new Point(100,200);
        Point p2 = new Point(100,200);
        System.out.println(p1==p2); //false，==比较的是地址
        //因为调用的是Point类重写之后的equals()，内部比较的是属性的值是否相同
        System.out.println(p1.equals(p2)); //true
        */

        String s1 = new String("hello");
        String s2 = new String("hello");
        //String重写equals()来比较字符串内容是否相同了
        System.out.println(s1.equals(s2)); //true

        StringBuilder builder1 = new StringBuilder("hello");
        StringBuilder builder2 = new StringBuilder("hello");
        //StringBuilder没有重写equals()，所以调用的还是Object的equals()，还是比较地址
        System.out.println(builder1.equals(builder2)); //false

        //s1与builder1的类型不同，所以equals()一定是false
        System.out.println(s1.equals(builder1)); //false









        /*
          输出引用变量时默认会调用Object类的toString()方法
          该方法返回的字符串格式为: 类的全称@地址
          但通常这个返回结果对我们的开发是没有任何意义
          我们真正想输出的应该是对象的属性值，Object类的toString()并不能满足需求
          因此常常需要重写toString()来返回具体的属性值
         */
        /*
        Point p = new Point(100,200);
        System.out.println(p); //输出引用变量时默认调用Object类的toString()
        System.out.println(p.toString());
         */
    }
}






















