package 项目.danei.src.first.day08;

public class ArrayMethodDemo {
    public static void main(String[] args) {
        /**
         * 需求:写一个程序,实现获取array数组中的最大值!
         * 思路:假设数组的第一个元素是最大值,存给变量max
         * 然后循环遍历,每次都需要让max与数组中的元素进行比较,如果max<array[i],则将当前
         * 位置上的数据赋值给max
         * 数组中所有元素比较完成后,然后打印max
         */
        int[] array = {20,500,700,10,4,11000,22222};
        int max = getArrayMax(array);
        System.out.println("最大值为:"+max);
        int[] arr1 = {1,33,52,1212,44};
        int max1 = getArrayMax(arr1);
        System.out.println(max1);
    }
    /**
     * 写一个功能(方法):方法名为getArrayMax
     * 通过外部传递进来一个int数组,然后方法内部实现计算逻辑求出数组中的最大值,并将最大值返回(return)
     */
    static int getArrayMax(int[] array) {
        int max = array[0]; //假设数组中第一个元素是最大值
        for (int i = 1; i < array.length; i++) { //依次遍历数组
            if (max < array[i]) { //判断max是否小于数组当前位置上的数据
                max = array[i]; //如果max小于数组当前位置上的数据,将数组中当前位置上的数据赋值给max
            }
        }
        return max;
    }
}
