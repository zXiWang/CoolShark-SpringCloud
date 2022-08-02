public class Teacher extends Person {
    Teacher(String name, int age, String gender, int salary) {
        super(name, age, gender, salary);
    }


    void teach() {
        System.out.println("在教书");
    }
}
