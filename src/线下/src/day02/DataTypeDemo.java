package day02;

//数据类型的演示
public class DataTypeDemo {
    public static void main(String[] args) {
        //类型间的转换:
        byte b1 = 5;
        byte b2 = 6;
        byte b3 = (byte) (b1 + b2);

        System.out.println(2 + 2);     //4
        System.out.println(2 + '2');   //52，2加上'2'的码50
        System.out.println('2' + '2'); //100，'2'的码50，加上，'2'的码50

        System.out.println('2'); //2，只有运算时才会转换为int类型








        /*
        int a = 5;
        long b = a;  //自动类型转换
        int c = (int)b; //强制类型转换

        long d = 5;   //自动类型转换
        double e = 5; //自动类型转换  //练习-----------4:41继续

        long f = 10000000000L;
        int g = (int)f;
        System.out.println(g); //1410065408，强转有可能发生溢出
        double h = 25.987;
        int i = (int)h;
        System.out.println(i); //25，强转有可能丢失精度
        */










        /*
        //5)char:字符型，2个字节
        char c1 = '女'; //字符女
        char c2 = 'f';  //字符f
        char c3 = '6';  //字符6
        char c4 = ' ';  //空格符
        //char c5 = 女; //编译错误，字符直接量必须放在单引号中
        //char c6 = ''; //编译错误，必须有字符
        //char c7 = '女性'; //编译错误，只能有1个字节

        char c8 = 97; //0到65535之间  //练习-----------3:55继续
                                //println()时会依据c8的数据类型来输出数据
        System.out.println(c8); //A，c8为char类型，所以会以字符的格式来输出

        char c9 = '\\'; //\为转义符
        System.out.println(c9); //\
         */








        /*
        //4)boolean:布尔型，1个字节
        boolean a = true;  //true为布尔型直接量
        boolean b = false; //false为布尔型直接量
        //boolean c = 250; //编译错误，数据类型不匹配
        */

        /*
        //3)double:浮点型，8个字节，很大很大很大
        double a = 25.678; //25.678为浮点数直接量，默认double型
        float b = 25.678F; //25.678F为float型

        double c=3.0,d=2.9;
        System.out.println(c-d); //0.10000000000000009，有可能发生舍入误差
        */














        /*
        //2)long:长整型，8个字节，很大很大很大
        long a = 25L; //25L为长整型直接量
        //long b = 10000000000; //编译错误，100亿默认为int型，但超出int范围了
        long c = 10000000000L; //100亿L为长整型

        //运算时若有可能溢出，建议在第1个数字后加L
        long d = 1000000000*2*10L;
        System.out.println(d); //200亿
        long e = 1000000000*3*10L;
        System.out.println(e); //不是300亿
        long f = 1000000000L*3*10;
        System.out.println(f); //300亿
        */









        /*
        //1)int:整型，4个字节，-21个多亿到21个多亿
        int a = 25; //25为整数直接量，默认为int类型
        //int b = 10000000000; //编译错误，100亿默认为int类型，但超出范围了
        //int c = 25.678; //编译错误，数据类型不匹配
        System.out.println(5/2); //2
        System.out.println(2/5); //0
        System.out.println(5/2.0); //2.5
        int c = 2147483647; //int的最大值
        c = c+1;
        System.out.println(c); //-2147483648(int的最小值)，发生溢出了
        */

    }
}















