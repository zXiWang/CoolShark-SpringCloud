package reflect;

public class ReflectDemo7 {
    public static void main(String[] args) throws ClassNotFoundException {

        Class cls=Class.forName("reflect.Person");

        boolean mark=cls.isAnnotationPresent(AutoRunClass.class);
        System.out.println("是否被标注了:"+mark);
    }
}
