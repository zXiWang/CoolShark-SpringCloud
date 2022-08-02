# API基础第二天扩展练习：

1. 定义字符串String path = "http://localhost:8088/myweb/reg?name=zhangsan&pwd=123456&nick=san&age=16";

   要求得到并输出：参数名:name 参数值：zhangsan

   ​ 参数名:pwd 参数值:  123456

   ​ 参数名:nick 参数值: san

   ​ 参数名:age 参数值: 16

   完整代码：

   ```java
   //测试类
   public class Test {
       public static void main(String[] args) {
           String path = "http://localhost:8088/myweb/reg?name=zhangsan&pwd=123456&nick=san&age=16";
   
           String[] arr = path.split("\\?");
           System.out.println("请求:"+arr[0]);
           System.out.println("参数:"+arr[1]);
           //拆分出每个参数，按照"&"拆分
           arr = arr[1].split("&");//[name=zhangsan, pwd=123456, ...]
           //遍历出每一个参数
           for(int i=0;i<arr.length;i++){
               String line = arr[i];
               //再将每个参数按照"="拆分为参数名和参数值
               String[] data = arr[i].split("=");
               System.out.println("参数名:"+data[0]+",参数值:"+data[1]);
           }
       }
   }
   ```
