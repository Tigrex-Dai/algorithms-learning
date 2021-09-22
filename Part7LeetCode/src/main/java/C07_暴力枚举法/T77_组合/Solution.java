/***********************************************************
 * @Description : 77.组合
 * https://leetcode-cn.com/problems/combinations/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C07_暴力枚举法.T77_组合;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private void calCombinations(int[] nums, int start, List<Integer> curList, List<List<Integer>> result, int k) {
        if (curList.size() == k) {
            result.add(new ArrayList<>(curList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            curList.add(nums[i]);
            calCombinations(nums, i + 1, curList, result, k);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        calCombinations(nums, 0, curList, result, k);
        return result;
    }
}
