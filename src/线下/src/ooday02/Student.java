package ooday02;

//学生类
public class Student {
    String name; //成员变量(整个类中)
    int age;
    String address;

    //给成员变量赋初值
    Student(String name, int age, String address) { //局部变量(当前方法中)
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void sayHi() {
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁了，家住" + address);
    }
}













