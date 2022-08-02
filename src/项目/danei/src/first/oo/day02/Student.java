package 项目.danei.src.first.oo.day02;

/**
 * 学生类
 */
public class Student extends Person  {
    //属性
    int stuID;

    Student(String name,int age,char gender,int stuID) {
        super(name, age, gender);
        this.stuID = stuID;
    }

    //行为
    void study() {
        System.out.println("好好学习!");
    }

    @Override
    void sayHi() {
        System.out.println("你好呀!我叫:"+name+",今年"+age+"岁了!我的学号是:"+stuID);
    }
}
