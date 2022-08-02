# API基础第一天扩展练习：

## 列表：

1. 生成一个4位验证码(数字和字母的组合)，输出到控制台并提示用户输入验证码，输入后若正确则提示验证码正确，若错误则提示验证码错误。注意：不区分大小写

## 参考：

1. 生成一个4位验证码(数字和字母的组合)，输出到控制台并提示用户输入验证码，输入后若正确则提示验证码正确，若错误则提示验证码错误。注意：不区分大小写

   参考代码：

   ```java
   package string;
   import java.util.Scanner;
   import java.util.Random;
   public class Test {
   	public static void main(String[] args) {
   		String line = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   		Random random = new Random();
   		String code = "";
   		for(int i=0;i<4;i++) {
   			int index = random.nextInt(line.length());
   			char c = line.charAt(index);
   			code+=c;
   		}
   		System.out.println("验证码:"+code);
   		Scanner scanner = new Scanner(System.in);
   		System.out.println("请输入上述验证码:");
   		String input = scanner.nextLine();
   		
   		if(code.equalsIgnoreCase(input)){
   			System.out.println("通过!");
   		}else{
   			System.out.println("失败!");
   		}
   	}
   }
   ```
   
   
