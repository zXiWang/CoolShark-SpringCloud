package exception;

/**
 * finally块
 * finally是异常处理机制的最后一块,它只能跟在try之后或者最后一个catch之后.
 * finally可以保证只要程序执行到try语句块中,无论try中的代码是否出现异常,finally都必
 * 定执行.
 * 通常我们使用finally去完成资源释放这类操作,比如IO中的关闭流操作.
 */
public class FinallyDemo {
    static class TestException {
        public static void main(String[] args) {
            int result;
            try {
                result = foo();
            } catch (Exception e) {
                System.out.println(e.getMessage());    //输出：我是finaly中的Exception
            }
        }
        //catch中的异常被抑制
        @SuppressWarnings("finally")
        public static int foo() throws Exception {
            try {
                int a = 5 / 0;
                return 1;
            } catch (ArithmeticException amExp) {
                throw new Exception("我将被忽略，因为下面的finally中抛出了新的异常");
            } finally {
                throw new Exception("我是finaly中的Exception");
            }
        }

//        public static void main(String[] args) {
//            int result;
//            try {
//                result = foo();
//                System.out.println(result);           //输出100
//            } catch (Exception e) {
//                System.out.println(e.getMessage());    //没有捕获到异常
//            }
//        }
//        //catch中的异常被抑制
//        @SuppressWarnings("finally")
//        public static int foo() throws Exception {
//            try {
//                int a = 5 / 0;
//                return 1;
//            } catch (ArithmeticException amExp) {
//                throw new Exception("我将被忽略，因为下面的finally中使用了return");
//            } finally {
//                return 100;
//            }
//        }
    }}






