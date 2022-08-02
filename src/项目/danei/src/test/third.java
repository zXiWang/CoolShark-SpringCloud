package 项目.danei.src.test;


import java.util.Arrays;

public class third {
    public static void main(String[] args) {

        int []arr={1,2,3,4,5,6,7,2,4};
        System.out.println(getArrayMax(arr));
    }
    static int getArrayMax(int[] a){
        Arrays.sort(a);
        return a[a.length-1];
    }
}
