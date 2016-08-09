package LeetCode;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            dict.put(nums[i], i);
        }
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (dict.containsKey(remain) && dict.get(remain) != i) {
                ans[0] = i;
                ans[1] = dict.get(remain);
                break;
            }
        }
        return ans;
    }
}
