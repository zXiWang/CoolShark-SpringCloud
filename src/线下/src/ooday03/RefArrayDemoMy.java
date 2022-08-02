package ooday03;

public class RefArrayDemoMy {
    public static void main(String[] args) {
        Student[] student = new Student[3];
        student[0] = new Student("傅泽远", 33, "江西省吉安市永丰县永丰县工业园区", "66");
        student[1] = new Student("sdf", 45, "cvgbb", "6");
        student[2] = new Student("gnf", 55, "cvgfngfn", "55");
        System.out.println(student[0].name);
        for (Student st : student) {
            st.sayHi();
        }
    }
}
