public class Test {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        Teacher[] teachers = new Teacher[3];
        for (int i = 0; i < 3; i++) {
            students[i] = new Student(i + "", i, i + "", i);
            teachers[i] = new Teacher(i + "", i, i + "", i);
        }
        for (Student student : students
        ) {
            System.out.print(student);
            student.run();
            student.eat();
            student.sleep();
            student.study();
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
            teacher.run();
            teacher.eat();
            teacher.sleep();
            teacher.teach();
        }

    }
}
