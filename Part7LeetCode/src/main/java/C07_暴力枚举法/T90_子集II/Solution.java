/***********************************************************
 * @Description : 90.子集II
 * https://leetcode-cn.com/problems/subsets-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C07_暴力枚举法.T90_子集II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private void calSubsets(int[] nums, int start, List<Integer> curList, List<List<Integer>> result){
        if(start == nums.length){
            return;
        }
        for(int i = start; i < nums.length; i++){
            curList.add(nums[i]);
            if(!result.contains(curList)){
                result.add(new ArrayList<>(curList));
            }
            calSubsets(nums, i + 1, curList, result);
            curList.remove(curList.size() - 1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        result.add(new ArrayList<>());
        calSubsets(nums, 0, curList, result);
        return result;
    }
}
