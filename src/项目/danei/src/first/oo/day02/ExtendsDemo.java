package 项目.danei.src.first.oo.day02;

/**
 * 继承的测试类
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        /**
         * 1.当学生类,老师类,医生类创建对象时,需要赋值时的过程比较繁琐,创建几个对象就需要赋值几次
         * 需要优化,创建对象时就可以快速初始化赋值!
         * 2.目前会发现三个子类中的构造方法,都有重复的赋值过程,需要优化!
         */
        Student s1 = new Student("张三",19,'男',1002);
        //变量能够调用出什么属性和行为,取决于当前类中有什么属性和行为
        /*s1.name = "张三";
        s1.age = 19;
        s1.gender = '男';
        s1.stuID = 1002;*/
        s1.eat();
        s1.study();
        System.out.println(s1.name+","+s1.age+"岁");

        Teacher t = new Teacher("TH",27,'男',30000);
        /*t.name = "TH"; //访问的是继承父类得来的属性
        t.age = 27; //访问的是继承父类得来的属性
        t.gender = '男'; //访问的是继承父类得来的属性
        t.salary = 30000; //访问的是本类自身特有的属性*/

        /*Person p = new Person();
        p.name = "";
        p.age = 0;
        p.gender = 0;
        p.eat();
        p.sleep();*/
        //p.stuID = 0;
        //p.salary = 0;

        /*Student[] students = new Student[3];
        students[0] = new Student("张同学",19,'男',1001);
        students[1] = new Student("李同学",20,'女',1002);
        students[2] = new Student("王同学",18,'男',1003);
        for (int i = 0; i < students.length; i++) {
            students[i].sayHi();
        }

        Teacher[] teachers = new Teacher[3];
        teachers[0] = new Teacher("张老师",29,'男',10010);
        teachers[1] = new Teacher("李老师",30,'女',10020);
        teachers[2] = new Teacher("王老师",28,'男',10030);
        for (int i = 0; i < teachers.length; i++) {
            teachers[i].sayHi();
        }

        Doctor[] doctors = new Doctor[3];
        doctors[0] = new Doctor("张医生",39,'男',30010);
        doctors[1] = new Doctor("李医生",40,'女',30020);
        doctors[2] = new Doctor("王医生",38,'男',30030);
        for (int i = 0; i < doctors.length; i++) {
            doctors[i].sayHi();
        }*/
        //声明 父   new 子
        Person[] p = new Person[9];
        p[0] = new Student("张同学",19,'男',1001);
        p[1] = new Student("李同学",20,'女',1002);
        p[2] = new Student("王同学",18,'男',1003);
        p[3] = new Teacher("张老师",29,'男',10010);
        p[4] = new Teacher("李老师",30,'女',10020);
        p[5] = new Teacher("王老师",28,'男',10030);
        p[6] = new Doctor("张医生",39,'男',30010);
        p[7] = new Doctor("李医生",40,'女',30020);
        p[8] = new Doctor("王医生",38,'男',30030);
        for (int i = 0; i < p.length; i++) {
            //编译期 : 调用的是父类型的sayHi()
            //运行期 : 调用的则是子类型的sayHi()
            p[i].sayHi();
        }
    }
}
