# API基础第一天标准练习：

## 列表：

1. String基本练习：使用new的方式创建字符串对象s并赋值为hello，声明字符串变量s1并赋值为字面量hello，使用==和equals()比较s与s1是否相同
2. String方法length()的练习：声明字符串型变量并赋值，获取字符串的长度并输出
3. String方法indexOf()/lastIndexOf()的练习：声明字符串型变量并赋值，检索当前字符串中给定字符串的位置并输出，检索给定字符串在当前字符串中最后一次出现的位置并输出
4. String方法substring()的练习：声明字符串型变量并赋值为一个网址，截取域中的名字并输出，截取整个域名并输出
5. String方法trim()的练习：声明字符串型变量并赋值，去除当前字符串两边的空白字符
6. String方法charAt()的练习：声明字符串型变量并赋值，返回当前字符串指定位置上的字符并输出
7. String方法startsWith()/endsWith()的练习：声明字符串型变量并赋值，判断当前字符串是否是以给定的字符串开始的/结束的
8. String方法toUpperCase()/toLowerCase()的练习：声明字符串型变量并赋值，将当前字符串中的英文部分转为全大写/全小写
9. String静态方法valueOf()的练习：声明一个整型变量和一个浮点型变量，将其数据类型转换为String并输出
10. StringBuilder的练习：append()追加、replace()替换、delete()删除、insert()插入

## 参考：

1. String基本练习：使用new的方式创建字符串对象s并赋值为hello，声明字符串变量s1并赋值为字面量hello，使用==和equals()比较s与s1是否相同

   参考代码：

   ```java
   /*
          常见面试题:
          String s = new String("hello");
          问:如上语句创建了几个对象?
          答:2个
            第一个:字面量"hello"
               ---java会创建一个String对象表示字面量"hello"，并将其存入常量池
            第二个:new String()
               ---new String()时会再创建一个字符串对象，并引用hello字符串的内容
         */
        String s = new String("hello");
        String s1 = "hello";
        System.out.println("s:"+s);   //hello
        System.out.println("s1:"+s1); //hello
        System.out.println(s==s1); //false，==比较的是地址是否相同
        //字符串实际开发中比较相等一般都是比较字符串的内容
        //因此我们需要使用equals()方法来比较两个字符串的内容
        System.out.println(s.equals(s1)); //equals()比较的是内容是否相同
   
   
   String s1 = "123abc"; //堆中有一个123abc字面量对象，同时缓存到常量池中
   //编译器在编译时，若发现是两个字面量相连，则会直接连接好并将结果保存起来
   //如下语句相当于: String s2 = "123abc";
   String s2 = "123"+"abc"; //直接复用常量池中的对象
   System.out.println(s1==s2); //true
   
   String s3 = "123";
   //因为s3是一个变量，所以在编译期并不会直接连接好
   String s4 = s3+"abc"; //创建一个新的对象存储123abc
   System.out.println(s4==s1); //false
   
   ```

2. String方法length()的练习：声明字符串型变量并赋值，获取字符串的长度并输出

   参考代码：

   ```java
   package apiday01;
   /**
    * int length():
    * 获取字符串的长度(字符个数)
    */
   public class LengthDemo {
       public static void main(String[] args) {
           String str = "我爱Java!";
           int len = str.length(); //获取str的长度
           System.out.println(len); //7
       }
   }
   ```

3. String方法indexOf()/lastIndexOf()的练习：声明字符串型变量并赋值，检索当前字符串中给定字符串的位置并输出，检索给定字符串在当前字符串中最后一次出现的位置并输出

   参考代码：

   ```java
   package apiday01;
   /*
    int indexOf(String str):
    检索给定字符串在当前字符串的开始位置
    int lastIndexOf(String str):
    检索给定字符串在当前字符串中最后一次出现的位置
    */
   public class IndexOfDemo {
       public static void main(String[] args) {
           //            0123456789012345
           String str = "thinking in java";
           int index = str.indexOf("in"); //检索in在字符串str中出现的开始位置
           System.out.println(index); //2
   
           index = str.indexOf("IN"); //当前字符串不包含给定内容IN，所以返回-1
           System.out.println(index); //-1
   
           index = str.indexOf("in",3); //从第4个字符开始找in第一次出现的位置
           System.out.println(index); //5
   
           index = str.lastIndexOf("in"); //找in最后一次出现的位置
           System.out.println(index); //9
       }
   }
   ```

4. String方法substring()的练习：设计getName()方法用于获取给定网址中的域名，并测试

   参考代码：

   ```java
   package apiday01;
   /**
    * String substring(int start,int end)
    * 截取当前字符串中指定范围内的字符串(含头不含尾--包含start，但不包含end)
    */
   public class SubstringDemo {
       public static void main(String[] args) {
           //            01234567890
           String str = "www.tedu.cn";
           String name = str.substring(4,8); //截取第4个到第7个----下标
           System.out.println(name); //tedu
           name = str.substring(4); //从第4个一直截取到字符串末尾----下标
           System.out.println(name); //tedu.cn
       }
   }
   ```

5. String方法trim()的练习：声明字符串型变量并赋值，去除当前字符串两边的空白字符

   参考代码：

   ```java
   package apiday01;
   /*
     String trim():
     去除当前字符串两边的空白字符
    */
   public class TrimDemo {
       public static void main(String[] args) {
           String str = "  hello world            ";
           System.out.println(str); //  hello world
           str = str.trim(); //去除当前字符串两边的空白字符
           System.out.println(str); //hello world
       }
   }
   ```

6. String方法charAt()的练习：声明字符串型变量并赋值，返回当前字符串指定位置上的字符并输出

   参考代码：

   ```java
   package apiday01;
   /**
    * char charAt(int index)
    * 返回当前字符串指定位置上的字符
    */
   public class CharAtDemo {
       public static void main(String[] args) {
           //            0123456789012345
           String str = "thinking in java";
           char c = str.charAt(9); //获取位置9所对应的字符
           System.out.println(c); //i
       }
   }
   ```

7. String方法startsWith()/endsWith()的练习：声明字符串型变量并赋值，判断当前字符串是否是以给定的字符串开始的/结束的

   参考代码：

   ```java
   package apiday01;
   /**
    * boolean startsWith(String str)
    * 判断当前字符串是否是以给定的字符串开始的
    * boolean endsWith(String str)
    * 判断当前字符串是否是以给定的字符串结尾的
    */
   public class StartsWithDemo {
       public static void main(String[] args) {
           String str = "thinking in java";
           boolean starts = str.startsWith("think"); //判断str是否是以think开头的
           System.out.println("starts:"+starts); //true
   
           boolean ends = str.endsWith(".png"); //判断str是否是以.png结尾的
           System.out.println("ends:"+ends); //false
       }
   }
   ```

8. String方法toUpperCase()/toLowerCase()的练习：声明字符串型变量并赋值，将当前字符串中的英文部分转为全大写/全小写

   参考代码：

   ```java
   package apiday01;
   /**
    * String toUpperCase()
    * 将当前字符串中的英文部分转为全大写
    * String toLowerCase()
    * 将当前字符串中的英文部分转为全小写
    */
   public class ToUpperCaseDemo {
       public static void main(String[] args) {
           String str = "我爱Java!";
           String upper = str.toUpperCase(); //将str中英文部分转为全大写
           System.out.println(upper); //我爱JAVA!
   
           String lower = str.toLowerCase(); //将str中英文部分转为全小写
           System.out.println(lower); //我爱java!
   
       }
   }
   ```

9. String静态方法valueOf()的练习：声明一个整型变量和一个浮点型变量，将其数据类型转换为String并输出

   参考代码：

   ```java
   package apiday01;
   /**
    * static String valueOf(数据类型 a):
    * 将其它数据类型转换为String
    */
   public class ValueOfDemo {
       public static void main(String[] args) {
           int a = 123;
           String s1 = String.valueOf(a); //将int型变量a转换为String类型并赋值给s1
           System.out.println("s1:"+s1); //123
   
           double dou = 123.456;
           String s2 = String.valueOf(dou); //将double型变量dou转换为String类型并赋值给s2
           System.out.println("s2:"+s2); //123.456
   
           String s3 = a + ""; //任何内容与字符串连接结果都是字符串，效率低
           System.out.println(s3); //123
       }
   }
   ```

10. StringBuilder的练习：append()追加、replace()替换、delete()删除、insert()插入

    ```java
    public class StringBuilderDemo {
        public static void main(String[] args) {
            String str = "好好学习java";
            //复制str的内容到builder中-----好好学习java
            StringBuilder builder = new StringBuilder(str);
    
            //append():追加内容----在末尾追加------增
            builder.append("，为了找个好工作");
            System.out.println(builder); //好好学习java，为了找个好工作
    
            //replace():替换部分内容，含头不含尾----改
            builder.replace(9,16,"就是为了改变世界");
            System.out.println(builder); //好好学习java，就是为了改变世界
    
            //delete():删除部分内容，含头不含尾-----删
            builder.delete(0,8); //删除下标0到7的
            System.out.println(builder); //，就是为了改变世界
    
            //insert():插入内容----插
            builder.insert(0,"活着"); //在下标0的位置插入"活着"
            System.out.println(builder); //活着，就是为了改变世界
    		
        }
    }
    ```

    
