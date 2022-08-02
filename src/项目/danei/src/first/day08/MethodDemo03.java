package 项目.danei.src.first.day08;

/**
 * 无参有返回值的方法使用测试类
 */
public class MethodDemo03 {
    public static void main(String[] args) {
        //如果需要接收方法产生的返回值,则需要根据方法定义时写的返回值类型,来声明变量进行接收
        //什么时候需要接收返回值?如果需要拿到返回的数据并进行下一步的代码逻辑处理时,则需要接收.
        double p = getPI(); //调用getPIng() 方法
        System.out.println(p);
        //如果仅仅是拿到数据并进行展示,没有后续的代码逻辑处理时,无需接收
        System.out.println(getPI());
    }

    /**
     * 实现一个功能,当别人调用此方法时,会返回给调用者一个数据PI
     * 语法 : 返回值类型  方法名() { ... }
     * 方法的返回值类型一定要和返回数据的类型一致
     * 当一个方法只需要返回给调用者数据,不需要调用者传递参数,则可以使用无参有返回值的方法
     */
    static double getPI() {
        //需要加上return 来返回数据
        return 3.1415926;
    }

}
