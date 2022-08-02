//package exception;
//
///**
// * 异常的抛出
// */
//public class ThrowDemo {
//    public static void main(String[] args) {
////        Person p = new Person();
//
//            /*
//                当我们调用一个含有throws声明异常抛出的方法时,编译器要求我们必须在调用该方法这里
//                添加异常处理机制,处理方式有两个可选项
//                1:主动使用try-catch捕获这个异常并处理
//                2:在当前方法上继续使用throws声明该异常的抛出给上层调用者(这里比如在main方法上
//                  继续使用throws声明抛出)
//                实际开发中选择那种取决于业务需求.
//
//             */
//        try {
//            p.setAge(1000);//满足语法,但是不满足业务
//        } catch (IllegalAgeException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("此人年龄"+p.getAge()+"岁");
//    }
//}
