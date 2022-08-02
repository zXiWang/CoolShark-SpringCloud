package exception;
/*
    JAVA 异常处理机制
    JAVA中所有的异常的顶级超类为:Throwable
    而Throwable下面定义了两个子类型:Error和Exception
    Error:表示错误，不可挽回的。通常描述的都是系统错误
    Exception:表示异常，可在程序运行过程中被处理掉的。
    我们通常仅在异常处理机制中处理Exception

    异常处理机制中的：try-catch
    语法:
    try{
        可能出现异常的代码片段
    }catch(XXXException e){
        处理try中出现XXXException的代码
    }

 */
public class TryCatchDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        try {//try语句块不能单独写，后面必须跟上catch或finally块
//            String str = null;
//            System.out.println(str.length());//JVM会实例化对应异常抛出
            String str = "";
            System.out.println(str.charAt(0));
            //try语句块中如果某句代码出现了异常，那么其后的所有代码均不执行！！
            System.out.println("!!!!!!!!!!!!");
//        }catch(NullPointerException e){//e就接受(捕获到)到24行这里出现的空指针异常实例
//            //针对该异常的解决办法(B计划)，实际要结合业务需求去处理
//            System.out.println("出现了空指针，并解决了");
//        //catch语句块可以定义多个，针对不同的异常有不同处理方式时我们可以分开捕获
//        }catch(StringIndexOutOfBoundsException e){
//            System.out.println("出现了字符串下标越界，并解决了");
//        }
        //当try语句块中几个异常有相同的处理方式时，我们可以合并到一个catch解决
        }catch(NullPointerException|StringIndexOutOfBoundsException e){
            System.out.println("出现空指针或字符串下标越界并解决了。");
        //当一类异常都使用相同解决方式时，我们可以采取捕获这一类异常的统一的超类
        }catch(Exception e){
            System.out.println("通用解决办法");
        }

        System.out.println("程序结束了");
    }
}
