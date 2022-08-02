package apiday01;

//String与StringBuilder的演示
public class StringStringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("a");
        for (int i = 0; i < 10000000; i++) { //1000万次
            s.append(i);
        }
        System.out.println("执行完毕");

        // //String不适合频繁修改内容(效率低)
        // String s = "a";
        // for(int i=0;i<10000000;i++){ //1000万次
        //     s = s+i; //每次修改都会在内存中分配新的对象
        // }
        // System.out.println("执行完毕");
    }
}





















