package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException {
//        List list = new ArrayList();
//        list.add("one");
//        System.out.println(list.size());
//
//        Class cls=String.class;
//
//        String name=cls.getName();
//        System.out.println(name);
//        name=cls.getSimpleName();
//        System.out.println(name);
//
//        Method[] methods=cls.getMethods();
//        for(Method method : methods) {
//            System.out.println(method.getName());
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个类名:");
        String className = scanner.nextLine();
        Class cls1 = Class.forName(className);

        //通过类对象获取String的一切信息
        //查看类名
        String name = cls1.getName();
        System.out.println(name);
        name = cls1.getSimpleName();
        System.out.println(name);

        Package p = cls1.getPackage();
        System.out.println("包名:" + p.getName());

        Method[] methods = cls1.getMethods();//获取String的所有方法
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

}
