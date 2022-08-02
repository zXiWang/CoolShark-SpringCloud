package 项目.danei.src.first.oo.day01;

/**
 * 学生类的模板: 代表所有学生对象
 *             类中放所有学生对象共有的属性
 *             类中放所有学生对象共有的行为
 * 模板类: 不放main功能/方法
 */
public class Student { //自定义的类,是一种数据类型(引用类型)
    //共有属性(数据),成员变量,作用域为整个类
    String name; //姓名
    int age; //年龄
    int stuID; //学号

    //类名 () { }
    Student(String name,int age,int stuID) { //有参构造方法
        this.name = name;
        this.age = age;
        this.stuID = stuID;
    }

    Student() { //无参构造方法

    }

    //共有行为
    void study() { //学习的行为
        //int a = 10; //局部变量 : 只能够在当前声明的方法中使用
        //this. this指的是当前对象,哪个对象来调用this就是哪个对象
        System.out.println(name +"在学习");
    }

    void sayHi() { //打招呼的行为
        System.out.println("我叫"+name+",年龄是"+age+"岁,学号是"+stuID);
    }
}
