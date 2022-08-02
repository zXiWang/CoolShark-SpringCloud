package exception;

/**
 * Finally常见面试问题
 * 1:请简述final,finally,finalize是什么?
 *  final参考克晶老师内容
 *  finally参考FinallyDemo案例笔记
 *  finalizes是一个方法,定义在Object中.该方法为一个对象生命周期中的最后一个方法.会被GC调用.当一个对象
 *  即将被GC回收前是调用该方法.
 */
public class FinallyDemo3 {
    public static void main(String[] args) {
        System.out.println(
                test("0")+","+test(null)+","+test("")
        );//0,1,2
    }
    public static int test(String str){
        try {
            return str.charAt(0)-'0';
        } catch (NullPointerException e) {
            return 1;
        } catch (Exception e){
            return 2;
        } finally {
            return 3;
        }
    }
}











