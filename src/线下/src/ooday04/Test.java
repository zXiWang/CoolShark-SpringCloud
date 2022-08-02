package ooday04;

//练习
public class Test {
    public static void main(String[] args) {
        Person[] ps = new Person[5];
        ps[0] = new Student("zhangsan", 25, "LF", "111"); //向上造型
        ps[1] = new Student("lisi", 24, "JMS", "222");
        ps[2] = new Teacher("wangwu", 35, "SD", 5000.0);
        ps[3] = new Teacher("zhaoliu", 45, "SX", 10000.0);
        ps[4] = new Doctor("sunqi", 55, "LF", "主任医师");
        for (int i = 0; i < ps.length; i++) { //遍历Person数组
            ps[i].sayHi(); //每个人跟大家问好
        }


        //1.能点出来什么，看引用的类型
        //2.重写方法被调用时，看对象的类型
        Student zs = new Student("zhangsan", 25, "LF", "111");
        zs.sayHi(); //调用Student类的sayHi()方法
        Person p = new Student("zhangsan", 25, "LF", "111");
        p.sayHi(); //调用Student类的sayHi()方法
    }
}

class Person {
    String name;
    int age;
    String address;

    Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void sayHi() {
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁了，家住" + address);
    }
}

class Student extends Person {
    String stuId; //学号

    Student(String name, int age, String address, String stuId) {
        super(name, age, address); //传的是name/age/address的值
        this.stuId = stuId;
    }

    void sayHi() {
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁了，家住" + address + "，学号为:" + stuId);
    }
}

class Teacher extends Person {
    double salary; //工资

    Teacher(String name, int age, String address, double salary) {
        super(name, age, address);
        this.salary = salary;
    }

    void sayHi() {
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁了，家住" + address + "，工资为:" + salary);
    }
}

class Doctor extends Person {
    String level; //职称

    Doctor(String name, int age, String address, String level) {
        super(name, age, address);
        this.level = level;
    }
}














