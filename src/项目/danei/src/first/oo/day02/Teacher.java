package 项目.danei.src.first.oo.day02;

/**
 * 老师类
 */
public class Teacher extends Person  {
    int salary;

    Teacher(String name,int age,char gender,int salary) {
        super(name, age, gender);
        this.salary = salary;
    }

    void teach() {

    }

    @Override
    void sayHi() {
        System.out.println("你好呀!我叫:"+name+",今年"+age+"岁了!我的工资是:"+salary);
    }
}
