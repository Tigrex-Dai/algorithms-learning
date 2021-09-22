/***********************************************************
 * @Description : 解决爬楼梯问题，使用基于动态规划的非递归法，最优
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 08:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs;


public class Solution3 {


    public int climbStairs(int n) {
        if(n == 1 || n ==2){
            return n;
        }
        /**
         * 记忆数组memory，用于存储子问题是否已经被访问
         */
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 20;
        int ways = new Solution3().climbStairs(n);
        System.out.println("一共有" + ways + "种爬楼梯的方法");
        System.out.println("非递归法，不需要递归函数");
    }
}

/**
 * 输出如下：
 * <p>
 * 一共有-10946种爬楼梯的方法
 * 非递归法，不需要递归函数
 */
