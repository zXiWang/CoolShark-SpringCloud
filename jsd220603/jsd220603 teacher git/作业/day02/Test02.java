package day02;
/**
 * 拆分字符串练习
 * 
 * 背景介绍:
 * 上网的时候,浏览器地址栏上有没有留意类似如下的地址形式?
 * https://search.jd.com/Search?keyword=usb&enc=utf-8&wq=usb&pvid=2fb014e3597c4712828926e2f35fa2b5
 * 
 * 上面这样的地址称为:URL(什么是URL可以百度一下~)
 * 
 * SE后期我们要写一个服务端,就要处理浏览器这样的请求.今天学习的字符串拆分方法在这里意义重大!
 * 
 * 
 * 单词记一记:
 * path   路径
 * age    年龄
 * local  本地的 
 * host   计算机中译为:主机，服务机
 * nickname 昵称
 * 
 * reg 是单词register的前三个字母，是注册的意思。
 * @author Xiloer
 *
 */
public class Test02 {
	public static void main(String[] args) {
		m1();
		m2();
	}
	public static void m1(){
		String path = "http://localhost:8088/myweb/reg?name=zhangsan";
		/*
		 * 将上述字符串按照"?"拆分为两部分并输出
		 * ?左侧在控制台输出内容为:
		 * 请求:http://localhost:8088/myweb/reg
		 *
		 * ?右侧在控制台输出内容为:
		 * 参数:name=zhangsan
		 *
		 */

	}

	public static void m2(){
		/*
		 * 进一步练习:
		 */
		//           请求部分                                     ?                 参数部分
		String path = "http://localhost:8088/myweb/reg?name=zhangsan&pwd=123456&nick=san&age=16";
		/*
		 * 先拆分出请求部分与参数部分
		 *
		 * 观察参数部分的格式，找出规律后再将每一个参数的名字和值
		 * 得到并输出为:
		 * 参数名:name,参数值:zhangsan
		 * 参数名:pwd,参数值:123456
		 * ...
		 */
	}
	/*
		m1提示:使用String的split方法按照"?"进行拆分。由于"?"在正则表达式中是量词，
		因此要想表示按照"?"拆分要使用转义字符"\\?"

		m2提示代码:
		m2需要用到的语句，先在注释中标注每句话的作用，并尝试按照正确顺序将下列代码
		放在m2方法中完成需求

		//【在这里标注该句代码意义】
		String[] data = path.split("\\?");

		//【在这里标注该句代码意义】
		for(int i=0;i<paras.length;i++){

		}

		//【在这里标注该句代码意义】
		String[] para = paras[i].split("=");

		//【在这里标注该句代码意义】
		System.out.println("请求:"+data[0]);

		//【在这里标注该句代码意义】
		System.out.println("参数名:"+para[0]+",参数值:"+para[1]);

		//【在这里标注该句代码意义】
		String[] paras = data[1].split("&");
	 */
}





