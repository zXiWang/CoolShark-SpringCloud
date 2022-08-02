package 项目.danei.src.first.day02;

public class varDemo {
    public static void main(String[] args) {
        //1.变量的声明
        int a; //声明一个int类型(整数)的一个变量,变量名为a
        int b,c,d; //声明三个int类型(整数)的变量,变量名分别为b,c,d
        //int a; 编译错误 : 因为已经声明了一个名为a的变量了,不能重复声明
        //2.变量的初始化
        int e = 300; //声明了一个int类型(整数)变量名为e,e里面存了300,声明的同时初始化
        int f; //声明了一个int类型(整数)变量名为f
        f = 150; //为f变量初始化,赋值为150
        System.out.println(f);
        //3.变量的使用
        int g = 5; //声明一个int类型(整数)的一个变量,变量名为g,g中存储了5
        int h = g + 5; //声明一个int类型(整数)的一个变量,变量名为h,h中存储了g + 5的和,最后和为10存储到了变量h中
        System.out.println("h"); //双引号,所见即所得
        System.out.println(h); //输出变量中存储的内容 ---> 10
        //System.out.println(H); //编译错误,严格区分大小写
        //4.变量的命名规则
        int $123;
        int _0123$;
        int asdbcio;
        //int 123a; 变量命名不能以数字开头
        int abc;
        int ABC;
        //int void; 变量命名,不能够使用关键字,关键字的颜色是蓝色的
        //int nianling; //不建议拼音的见名知意
        //int 年龄; //不建议汉字的见名知意
        int age; //建议英文的见名知意
        //变量的命名 遵循小驼峰命名法,整个名称的首字母小写,但第二个(及以后)单词的首字母大写
        int myAge;
        //类名的命名 遵循大驼峰命名法,整个名称的首字母大写,第二个(及以后)单词的首字母也都大写

    }
}
















