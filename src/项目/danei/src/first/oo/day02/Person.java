package 项目.danei.src.first.oo.day02;

/**
 * 人类
 */
public class Person {
    //属性
    String name;
    int age;
    char gender;

    Person(String name,int age,char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //行为
    void eat() {
        System.out.println("吃吃吃");
    }

    void sleep() {
        System.out.println("睡睡睡");
    }

    void sayHi() {
        System.out.println("你好呀!我叫:"+name+",今年"+age+"岁了!");
    }
}
