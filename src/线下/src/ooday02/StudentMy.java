package ooday02;

public class StudentMy {
    String name;
    int age;
    String address;

    StudentMy(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void sayHi() {
        System.out.println("我叫" + name + ",今年" + age + "岁了,住在" + address);
    }
}
