package ��Ŀ.danei.src.first.oo.day02;

/**
 * �̳еĲ�����
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        /**
         * 1.��ѧ����,��ʦ��,ҽ���ഴ������ʱ,��Ҫ��ֵʱ�Ĺ��̱ȽϷ���,���������������Ҫ��ֵ����
         * ��Ҫ�Ż�,��������ʱ�Ϳ��Կ��ٳ�ʼ����ֵ!
         * 2.Ŀǰ�ᷢ�����������еĹ��췽��,�����ظ��ĸ�ֵ����,��Ҫ�Ż�!
         */
        Student s1 = new Student("����",19,'��',1002);
        //�����ܹ����ó�ʲô���Ժ���Ϊ,ȡ���ڵ�ǰ������ʲô���Ժ���Ϊ
        /*s1.name = "����";
        s1.age = 19;
        s1.gender = '��';
        s1.stuID = 1002;*/
        s1.eat();
        s1.study();
        System.out.println(s1.name+","+s1.age+"��");

        Teacher t = new Teacher("TH",27,'��',30000);
        /*t.name = "TH"; //���ʵ��Ǽ̳и������������
        t.age = 27; //���ʵ��Ǽ̳и������������
        t.gender = '��'; //���ʵ��Ǽ̳и������������
        t.salary = 30000; //���ʵ��Ǳ����������е�����*/

        /*Person p = new Person();
        p.name = "";
        p.age = 0;
        p.gender = 0;
        p.eat();
        p.sleep();*/
        //p.stuID = 0;
        //p.salary = 0;

        /*Student[] students = new Student[3];
        students[0] = new Student("��ͬѧ",19,'��',1001);
        students[1] = new Student("��ͬѧ",20,'Ů',1002);
        students[2] = new Student("��ͬѧ",18,'��',1003);
        for (int i = 0; i < students.length; i++) {
            students[i].sayHi();
        }

        Teacher[] teachers = new Teacher[3];
        teachers[0] = new Teacher("����ʦ",29,'��',10010);
        teachers[1] = new Teacher("����ʦ",30,'Ů',10020);
        teachers[2] = new Teacher("����ʦ",28,'��',10030);
        for (int i = 0; i < teachers.length; i++) {
            teachers[i].sayHi();
        }

        Doctor[] doctors = new Doctor[3];
        doctors[0] = new Doctor("��ҽ��",39,'��',30010);
        doctors[1] = new Doctor("��ҽ��",40,'Ů',30020);
        doctors[2] = new Doctor("��ҽ��",38,'��',30030);
        for (int i = 0; i < doctors.length; i++) {
            doctors[i].sayHi();
        }*/
        //���� ��   new ��
        Person[] p = new Person[9];
        p[0] = new Student("��ͬѧ",19,'��',1001);
        p[1] = new Student("��ͬѧ",20,'Ů',1002);
        p[2] = new Student("��ͬѧ",18,'��',1003);
        p[3] = new Teacher("����ʦ",29,'��',10010);
        p[4] = new Teacher("����ʦ",30,'Ů',10020);
        p[5] = new Teacher("����ʦ",28,'��',10030);
        p[6] = new Doctor("��ҽ��",39,'��',30010);
        p[7] = new Doctor("��ҽ��",40,'Ů',30020);
        p[8] = new Doctor("��ҽ��",38,'��',30030);
        for (int i = 0; i < p.length; i++) {
            //������ : ���õ��Ǹ����͵�sayHi()
            //������ : ���õ����������͵�sayHi()
            p[i].sayHi();
        }
    }
}
