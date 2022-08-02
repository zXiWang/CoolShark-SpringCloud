public class Student extends Person {
    Student(String name, int age, String gender, int salary) {
        super(name, age, gender, salary);
    }

    void study() {
        System.out.println("在学习");
    }
}
