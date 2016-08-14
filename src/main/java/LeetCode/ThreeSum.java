package LeetCode;

import Tools.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static void main(String[] argv) {
        Integer nums[] = {-2, 0, 1, 1, 2};
        ThreeSum ts = new ThreeSum();
        List<List<Integer>> ans = ts.threeSum(nums);
        System.out.println(ans.toString());
    }

    public List<List<Integer>> threeSum(Integer[] nums) {
        int n = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        if (n > 2) {
            Arrays.sort(nums);
            Tools.println(nums);
            for (int i = 0; i < n - 2; i++) {
                int j = i + 1;
                int k = n - 1;
                int remain = 0 - nums[i];
                while (j < k) {
                    int sum = nums[j] + nums[k];
                    if (sum > remain) {
                        k--;
                    } else if (sum < remain) {
                        j++;
                    } else {
                        ArrayList<Integer> line = new ArrayList<>(3);
                        line.add(nums[i]);
                        line.add(nums[j]);
                        line.add(nums[k]);
                        ans.add(line);
                        k--;
                        j++;
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
