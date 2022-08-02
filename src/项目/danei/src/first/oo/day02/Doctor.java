package 项目.danei.src.first.oo.day02;

/**
 * 医生类
 */
public class Doctor extends Person {
    int level; //职级

    Doctor(String name,int age,char gender,int level) {
        super(name, age, gender);
        this.level = level;
    }

    void operation() { //手术

    }

    @Override
    void sayHi() {
        System.out.println("你好呀!我叫:"+name+",今年"+age+"岁了!我的职称是:"+level);
    }
}
