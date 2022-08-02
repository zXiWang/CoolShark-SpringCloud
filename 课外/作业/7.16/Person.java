public abstract class Person {
    private String name;
    private int age;
    private String gender;
    private int salary;

    Person(String name, int age, String gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String toString() {
        return name + "今年" + age + "岁,是" + gender + "的,工资" + salary + "，正";
    }

    void eat() {
        System.out.println("在吃饭");
    }

    void sleep() {
        System.out.println("在睡觉去");
    }

    void run() {
        System.out.println("在跑步");
    }
}
