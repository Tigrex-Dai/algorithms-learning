/***********************************************************
 * @Description : 递归解决爬楼梯问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 08:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs;

public class Solution1 {

    public static int num;

    public int climbStairs(int n) {
        num++;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        int n = 20;
        int ways = new Solution1().climbStairs(n);
        System.out.println("一共有" + ways + "种爬楼梯的方法");
        System.out.println("一共进入递归函数" + num + "次");
    }
}

/**
 * 输出如下：
 * <p>
 * 一共有10946种爬楼梯的方法
 * 一共进入递归函数21891次
 */
