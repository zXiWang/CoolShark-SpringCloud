package reflect;

import java.util.Scanner;

public class ReflectDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Person p=new Person();
        System.out.println(p);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个类名:");
        String className = scanner.nextLine();
        Class cls = Class.forName(className);
//        Class cls=Class.forName("reflect.Person");
        Object obj=cls.newInstance();
        System.out.println(obj.getClass());
    }
}
