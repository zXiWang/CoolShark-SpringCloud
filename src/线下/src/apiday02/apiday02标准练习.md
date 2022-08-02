# API基础第二天标准练习：

## 列表：

1. StringBuilder()的练习：声明StringBuilder型变量，练习append()追加数据、replace()替换数据、delete()删除数据、insert()插入数据
2. String正则表达式matches()方法的练习：声明字符串型变量并赋值为你的邮箱，练习matches()匹配邮箱是否正确
3. String正则表达式split()方法的练习：声明字符串型变量并赋值，将当前字符串使用点(.)来进行拆分，输出拆分后的数据
4. String正则表达式replaceAll()方法的练习：声明字符串型变量并赋值，将当前字符串中的数字部分替换为#NUMBER#，并输出

## 参考：

1. StringBuilder()的练习：声明StringBuilder型变量，练习append()追加数据、replace()替换数据、delete()删除数据、insert()插入数据

   参考代码：

   ```java
   package string;
   public class StringBuilderDemo {
       public static void main(String[] args) {
           String str = "好好学习java";
           //默认表示一个空字符串
   //        StringBuilder builder = new StringBuilder();
           //将给定的字符串内容复制到StringBuilder内部，基于它进行修改
   //        StringBuilder builder = new StringBuilder(str);
           StringBuffer builder = new StringBuffer(str);
           /*
               好好学习java
               好好学习java,为了找个好工作!
               append():追加操作
            */
           builder.append(",为了找个好工作!");
           String s = builder.toString();
           System.out.println(s);
           /*
               好好学习java,为了找个好工作!
               好好学习java,就是为了改变世界!
               replace():替换,将指定范围内的字符串替换为给定字符串
            */
           builder.replace(9,16,"就是为了改变世界");
   //        System.out.println(builder.toString());
           System.out.println(builder);
           /*
               好好学习java,就是为了改变世界!
               ,就是为了改变世界!
               delete():删除，删除指定范围内的字符串
            */
           builder.delete(0,8);
           System.out.println(builder);
           /*
               ,就是为了改变世界!
               活着,就是为了改变世界!
               insert():在指定位置插入给定字符串
            */
           builder.insert(0,"活着");
           System.out.println(builder);
       }
   }
   ```

2. String正则表达式matches()方法的练习：声明字符串型变量并赋值为你的邮箱，练习matches()匹配邮箱是否正确

   参考代码：

   ```java
   package apiday02;
   /**
    * String支持正则表达式的方法之一:
    * boolean matches(String regex)
    * 使用给定的正则表达式验证当前字符串是否符合格式要求
    * 注意!给定的正则表达式就算不加边界匹配符也是做全匹配验证的
    */
   public class MatchesDemo {
       public static void main(String[] args) {
           /*
               邮箱的正则表达式:
               [a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+
            */
           String email = "wangkj@tedu.cn";
           String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
           boolean match = email.matches(regex);
           if(match){
               System.out.println("是邮箱");
           }else{
               System.out.println("不是邮箱");
           }
       }
   }
   ```

3. String正则表达式split()方法的练习：声明字符串型变量并赋值，将当前字符串使用点(.)来进行拆分，输出拆分后的数据

   参考代码：

   ```java
   package apiday02;
   import java.util.Arrays;
   /**
    * String支持正则表达式的方法之二:
    * String[] split(String regex)
    * 将当前字符串按照满足正则表达式的部分进行拆分，将拆分后的每部分以数组形式返回。
    */
   public class SplitDemo {
       public static void main(String[] args) {
   //        String str = "abc123def456ghi";
   //        String[] arr = str.split("[0-9]+");
           /*
               如果连续出现可拆分项，则中间会拆分出一个空字符串。如果字符串在开始就是可拆分项
               则首先会拆分出一个空字符串。
               注意，如果在字符串末尾连续匹配到可拆分项，所有拆分出的空字符串都会被忽略。
            */
           String str = "..abc.def.ghi........";
           String[] arr = str.split("\\.");
           System.out.println(arr.length);
           System.out.println(Arrays.toString(arr));
       }
   }
   ```

4. String正则表达式replaceAll()方法的练习：声明字符串型变量并赋值，将当前字符串中的数字部分替换为#NUMBER#，并输出

   参考代码：

   ```java
   package apiday02;
   /**
    * String支持正则表达式方法三
    * String replaceAll(String regex,String str)
    * 将当前字符串中满足正则表达式的部分替换为给定内容
    *
    */
   public class ReplaceAllDemo {
       public static void main(String[] args) {
           String str = "abc123def456ghi";
           //将当前字符串中的数字部分替换为#NUMBER#
           str = str.replaceAll("[0-9]+","#NUMBER#");
           System.out.println(str);
       }
   }
   ```

5. 练习toString()和equals()

   完整代码：

   ```java
   package object;
   /**
    * 使用当前类测试常被子类重写的Object相关方法.
    * 当前类表示直角坐标系上的一个点
    */
   public class Point {
       private int x;
       private int y;
   
       public Point(int x, int y) {
           this.x = x;
           this.y = y;
       }
   
       public int getX() {
           return x;
       }
   
       public void setX(int x) {
           this.x = x;
       }
   
       public int getY() {
           return y;
       }
   
       public void setY(int y) {
           this.y = y;
       }
   
       public String toString() {
           return "(" + x + "," + y +")";
       }
   
       public boolean equals(Object o) {
           if (this == o) return true;
           if (o == null || getClass() != o.getClass()) return false;
           Point point = (Point) o;
           return x == point.x &&
                   y == point.y;
       }
   }
   
   package object;
   /**
    * Object是所有类的顶级超类.里面有几个经常被子类重写的方法,其中包括toString和equals
    */
   public class Demo {
       public static void main(String[] args) {
           Point p = new Point(1,2);
           /*
               Object已经实现了toString方法.直接继承下来时返回的字符串内容为当前对象的
               地址信息.格式为:类名@地址.
               toString方法实际开发中很少直接写代码去调用它,都是在不经意间被自动执行的.
               例如在使用System.out.println输出时.与字符串连接操作时.
            */
           //System.out.println(Object obj)输出给定对象toString返回的字符串到控制台
           System.out.println(p);
   
           //字符串连接操作时,会将非String类型的对象调用toString转换为字符串后拼接.
           String line = "这是一个Point:" + p;
           System.out.println(line);
   
           Point p2 = new Point(1,2);
           System.out.println("p2:"+p2);
           /*
               对于引用类型而言,变量保存的值是对象的地址.
               ==比较是比较两个变量的值是否相等,因此对于引用类型而言就是比较地址是否相等,
               那么意思就是比较是否为同一个对象.
   
               equals是Object定义的另一个方法,意图是比较两个对象的内容是否相同.但是如果
               子类不重写这个方法,则没有实际意义,因为Object实现时内部仍然是用==比较的!
            */
           System.out.println(p == p2);//false
           System.out.println(p.equals(p2));//true
       }
   }
   ```

6. 练习parseInt()和parseDouble()

   完整代码：

   ```java
   package integer;
   /**
    * 包装类常用功能
    */
   public class IntegerDemo2 {
       public static void main(String[] args) {
           //1可以通过包装类获取其表示的基本类型的取值范围
           //获取int的最大值和最小值?
           int imax = Integer.MAX_VALUE;
           System.out.println(imax);
           int imin = Integer.MIN_VALUE;
           System.out.println(imin);
   
           long lmax = Long.MAX_VALUE;
           System.out.println(lmax);
           long lmin = Long.MIN_VALUE;
           System.out.println(lmin);
   
           /*
               字符串转换为基本类型的前提是该字符串正确描述了基本类型可以保存的值,否则
               会抛出异常:NumberFormatException
            */
           String str = "123";
           //String str = "123.123";//这个字符串不能解析为int值!
           int d = Integer.parseInt(str);
           System.out.println(d);//123
           double dou = Double.parseDouble(str);
           System.out.println(dou);//123.123
       }
   }
   ```
