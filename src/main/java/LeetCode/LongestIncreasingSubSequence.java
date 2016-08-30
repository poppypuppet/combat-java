package LeetCode;

import Tools.Printer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 */
public class LongestIncreasingSubSequence {
    public static void main(String[] argv) {
        LongestIncreasingSubSequence l = new LongestIncreasingSubSequence();
        Integer[] nums = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        Printer.println(nums);
        System.out.println(l.lengthOfLIS_BS_II(nums));
    }

    //  O(n*n) DP
    // 我们维护一个一维dp数组，其中dp[i]表示以nums[i]为结尾的最长递增子串的长度，
    // 对于每一个nums[i]，我们从第一个数再搜索到i，如果发现某个数小于nums[i]，我们更新dp[i]，
    // 更新方法为dp[i] = max(dp[i], dp[j] + 1)，即比较当前dp[i]的值和那个小于num[i]的数的dp值加1的大小，
    // 我们就这样不断的更新dp数组，到最后dp数组中最大的值就是我们要返回的LIS的长度，
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 复杂度
     * 时间 O(NlogN) 空间 O(N)
     * 思路是，我们先建立一个数组ends，把首元素放进去，然后比较之后的元素，
     * 1.如果遍历到的新元素比ends数组中的首元素小的话，替换首元素为此新元素，
     * 2.如果遍历到的新元素比ends数组中的末尾元素还大的话，将此新元素添加到ends数组末尾(注意不覆盖原末尾元素)。
     * 3.如果遍历到的新元素比ends数组首元素大，比尾元素小时，此时用二分查找法找到第一个不小于此新元素的位置，覆盖掉位置的原来的数字，
     * 以此类推直至遍历完整个nums数组，此时ends数组的长度就是我们要求的LIS的长度，特别注意的是ends数组的值可能不是一个真实的LIS，
     * 比如若输入数组nums为{4, 2， 4， 5， 3， 7}，那么算完后的ends数组为{2， 3， 5， 7}，
     * 可以发现它不是一个原数组的LIS，只是长度相等而已，千万要注意这点。
     * 对于这个数组表示,位于index=i, 长度为i递增序列的结尾的最小值
     */

    public int lengthOfLIS_BS_II(Integer[] nums) {
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            Integer num = nums[i];
            int index = findNextPos(ans, num);
            if (index >= ans.size()) {
                ans.offerLast(num);
            } else {
                ans.set(index, num);
            }
            System.out.println(ans);
        }
        return ans.size();
    }

    /**
     * 此时用二分查找法找到第一个不小于此新元素的位置
     *
     * @param nums
     * @param target
     */
    public int findNextPos(List<Integer> nums, Integer target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = nums.get(mid);
            if (midVal < target) {
                left = mid + 1;
            } else if (midVal > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        System.out.println(String.format("left=%d, right=%d", left, right));
        return left;
    }
}
