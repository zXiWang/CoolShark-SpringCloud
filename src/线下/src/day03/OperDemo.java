package day03;

//运算符的演示
public class OperDemo {
    public static void main(String[] args) {
        /*
          3.逻辑运算符:
            1)&&:短路与(并且)，两边都为真则为真，见false则false
                 ---当第1个条件为false时，则发生短路(后面的不执行了)
              ||:短路或(或者)，有真则为真，见true则true
                 ---当第1个条件为true时，则发生短路(后面的不执行了)
               !:逻辑非(取反)，非真则假，非假则真
            2)逻辑运算是建立在关系运算的基础之上的
              逻辑运算的结果也是boolean型
         */
        int a = 5, b = 10, c = 5;
        boolean b2 = a < b || c++ > 2;
        System.out.println(b2); //true
        System.out.println(c);  //5，发生短路了

        /*
        boolean b1 = a>b && c++>2;
        System.out.println(b1); //false
        System.out.println(c);  //5，发生短路了
        */









        /*
        boolean b1 = b>=a && b<c;
        System.out.println(b1);          //true&&false=false
        System.out.println(b<=c && b>a); //false&&true=false
        System.out.println(a==b && c>b); //false&&false=false
        System.out.println(b!=c && a<b); //true&&true=true

        System.out.println(b>=a || b<c); //true||false=true
        System.out.println(b<=c || b>a); //false||true=true
        System.out.println(b!=c || a<b); //true||true=true
        System.out.println(a==b || c>b); //false||false=false

        boolean b2 = !(a<b);
        System.out.println(b2);     //!true=false
        System.out.println(!(a>b)); //!false=true
        */



        /*
          2.关系运算符:
            1)>(大于)、<(小于)
              >=(大于或等于)、<=(小于或等于)
              ==(等于)、!=(不等于)
            2)关系运算的结果为boolean型，
              关系成立则为true，关系不成立则为false
         */
        /*
        int a=5,b=10,c=5;
        boolean b1 = a>b;
        System.out.println(b1);   //false
        System.out.println(c<b);  //true
        System.out.println(a>=c); //true
        System.out.println(a<=b); //true
        System.out.println(a==c); //true
        System.out.println(a!=c); //false
        System.out.println(a+c>10); //false
        System.out.println(a%2==0); //false
        System.out.println(a++>5); //false--------a自增1变为6
        System.out.println(a++>5); //true---------a自增1变为7
        */







        /*
          1.算术运算符:+,-,*,/,%,++,--
            1)%:取模/取余，余数为0即为整除
            2)++/--:自增1/自减1，可在变量前也可在变量后
              2.1)单独使用时，在前在后都一样
              2.2)被使用时，在前在后不一样
                    a++的值为a----------(a--的值为a)
                    ++a的值为a+1--------(--a的值为a-1)
         */
        /*
        int a=5,b=5;
        int c = a--; //1)将a--的值5赋值给c  2)a自减1变为4
        int d = --b; //1)将--b的值4赋值给d  2)b自减1变为4
        System.out.println(a); //4
        System.out.println(b); //4
        System.out.println(c); //5
        System.out.println(d); //4
        */
        /*
        int a=5,b=5;
        a--;
        --b;
        System.out.println(a); //4
        System.out.println(b); //4
        */

        /*
        int a=5,b=5;
        int c = a++; //1)将a++的值5赋值给c  2)a自增1变为6
                     //----a++的值为a(5)，所以c的值就是5
        int d = ++b; //1)将++b的值6赋值给d  2)b自增1变为6
                     //++b的值为b+1(6)，所以d的值就是6
        System.out.println(a); //6
        System.out.println(b); //6
        System.out.println(c); //5
        System.out.println(d); //6
        */
        /*
        int a=5,b=5;
        a++; //相当于a=a+1
        ++b; //相当于b=b+1
        System.out.println(a); //6
        System.out.println(b); //6
        */









        /*
        System.out.println(5%2); //1，商2余1
        System.out.println(8%2); //0，商4余0----整除
        System.out.println(2%8); //2，商0余2
         */
    }
}






















