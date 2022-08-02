package 项目.danei.src.first.oo.day01;

/**
 * 学生的测试类
 * 测试类中添加main方法
 */
public class StudentDemo {
    public static void main(String[] args) {
        /*//创建一个学生对象,将对象存储到s1这个引用类型的变量中
        Student s1 = new Student("张同学",25,1001);
        //这个点就相当于调用,调用的内容取决于类的属性
        *//*s1.name = "张同学";
        s1.age = 25;
        s1.stuID = 1001;*//*
        s1.sayHi();

        Student s2 = new Student("李同学",26,1002);
        //这个点就相当于调用,调用的内容取决于类的属性
        *//*s2.name = "李同学";
        s2.age = 26;
        s2.stuID = 1002;*//*
        s2.sayHi();

        Student s3 = new Student("王同学",27,1003);
        //这个点就相当于调用,调用的内容取决于类的属性
        *//*s3.name = "王同学";
        s3.age = 27;
        s3.stuID = 1003;*//*
        s3.sayHi();*/
        /*Student s1 = new Student();
        s1.name = "周同学";
        s1.age = 40;
        s1.stuID = 1001;
        System.out.println(s1.name);
        System.out.println(s1.age);
        System.out.println(s1.stuID);*/
        /*int[] a = new int[3];
        a[0] = 99;
        a[1] = 100;
        a[2] = 101;

        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);*/
        Student[] students = new Student[3];
        students[0] = new Student();
        students[0].name = "黄同学";
        System.out.println(students[0].name);
        System.out.println(students[0].age);
        System.out.println(students[0].stuID);
        System.out.println(students[0]);
        System.out.println(students[1]);
        System.out.println(students[2]);
    }
}
