package apiday01;

//StringBuilder的演示
public class StringBuilderDemo {
    public static void main(String[] args) {
        String str = "好好学习Java";
        //复制str的内容到builder中--------好好学习Java
        StringBuilder builder = new StringBuilder(str);

        //append():追加内容-----在末尾追加
        builder.append("，为了找个好工作");
        System.out.println(builder); //好好学习Java，为了找个好工作

        //replace():替换部分内容(含头不含尾)
        //将下标为9到15的内容替换为---就是为了改变世界
        builder.replace(9, 16, "就是为了改变世界");
        System.out.println(builder); //好好学习Java，就是为了改变世界

        //delete():删除部分内容(含头不含尾)
        builder.delete(0, 8); //删除下标为0到7的
        System.out.println(builder); //，就是为了改变世界

        //insert():插入内容
        builder.insert(0, "活着"); //在下标为0的位置插入活着
        System.out.println(builder); //活着，就是为了改变世界



        /*
        //StringBuilder的创建方式:
        StringBuilder builder1 = new StringBuilder(); //空字符串
        StringBuilder builder2 = new StringBuilder("abc"); //abc串
        String str = "abc";
        StringBuilder builder3 = new StringBuilder(str); //abc串

        String str2 = builder3.toString(); //将builder3转换为String类型
        */
    }
}











