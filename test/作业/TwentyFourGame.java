package 作业;

import java.util.*;

public class TwentyFourGame {
    static Set<Stack<String>> hashSet = new HashSet<Stack<String>>(); // 去除重复项
    static char[] syms = new char[]{'+', '-', '*', '/'};
    static boolean[] visited = new boolean[3]; // 数字是否被访问过， true为访问过，false没有
    static Stack<String> fu = new Stack<String>(); // 装载公式的栈
    static int[][] all = new int[24][]; // 装载4个数字的全排列的数组
    static int countOfall = 0; // all数组的索引

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[4];
        System.out.println("输入四个数字(空格分割):");
        for (int i = 0; i < nums.length; i++) {

            nums[i] = sc.nextInt();
        }
        permutations(nums, 0, nums.length);

        nums = new int[3];
        for (int i = 0; i < all.length; i++) {
            int numOne = all[i][0];
            nums = Arrays.copyOfRange(all[i], 1, all[i].length);
            fu.push(Integer.toString(numOne));
            resolve(numOne, nums);
            fu.pop();
        }

        /**
         * 输出所有符合的项
         */
        if (!hashSet.isEmpty()) {
            for (Stack<String> st : hashSet) {
                if (!st.isEmpty())
                    System.out.println(st);
            }
        } else System.out.println("无解");
    }

    /*
    全排列
    */
    public static void permutations(int[] nums, int start, int end) {
        if (start == end) {
            all[countOfall++] = Arrays.copyOf(nums, nums.length);
        } else {
            for (int i = start; i < end; i++) {
                swap(nums, start, i);
                permutations(nums, start + 1, end);
                swap(nums, start, i);
            }
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    /*
    计算24点的代码
    num为四个数字的第一个数，nums装载其他3个数
    需要注意的是除法，因为除法要必须是整除，所以得额外判断，如果不能整除
    直接放弃这个运算，break出来
    */
    public static void resolve(int num, int[] nums) {
        if (num == 24 && fu.size() == 7) {
            hashSet.add((Stack<String>) fu.clone());
            return;
        } else if (fu.size() == 7) { // 栈中有个四个数和三个运算符
            //但并不符合规则
            return;
        }
        for (int i = 0; i < nums.length; i++) {

            if (!visited[i]) {
                for (int j = 0; j < syms.length; j++) {
                    if (syms[j] == '+') {
                        visited[i] = true;
                        fu.push("+");
                        fu.push(Integer.toString(nums[i]));
                        resolve(nums[i] + num, nums);
                        visited[i] = false;
                        fu.pop();
                        fu.pop();
                    } else if (syms[j] == '-') {
                        visited[i] = true;
                        fu.push("-");
                        fu.push(Integer.toString(nums[i]));
                        resolve(num - nums[i], nums);
                        visited[i] = false;
                        fu.pop();
                        fu.pop();
                    } else if (syms[j] == '*') {
                        visited[i] = true;
                        fu.push("*");
                        fu.push(Integer.toString(nums[i]));
                        resolve(nums[i] * num, nums);
                        visited[i] = false;
                        fu.pop();
                        fu.pop();
                    } else {
                        if (num % nums[i] == 0) {
                            visited[i] = true;
                            fu.push("/");
                            fu.push(Integer.toString(nums[i]));
                            resolve(num / nums[i], nums);
                            visited[i] = false;
                            fu.pop();
                            fu.pop();
                        } else break;
                    }
                }
            }
        }
    }

}
