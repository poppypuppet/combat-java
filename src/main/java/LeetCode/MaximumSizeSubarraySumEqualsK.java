package LeetCode;

import java.util.HashMap;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {
    public static void main(String[] argv) {
        int[] nums = {-2, -1, 2, 1};
        MaximumSizeSubarraySumEqualsK m = new MaximumSizeSubarraySumEqualsK();
        System.out.println(m.maxSubArrayLen(nums, 1));
    }

    /**
     * 建立累积和的好处显而易见，如果当前累积和正好等于k，那么从开头到此位置的子数组就是一个符合要求的解，
     * 但不一定是最长的子数组，而使用哈希表来建立累积和和其坐标之间的映射，我们就从题目中给的例子进行分析：
     * nums: [1, -1, 5, -2, 3], k = 3
     * sums: [1, 0, 5, 3, 6]
     * 我们可以看到累积和的第四个数字为3，和k相同，则说明前四个数字就是符合题意的一个子数组，再来看第二个例子：
     * nums: [-2, -1, 2, 1], k = 1
     * sums: [-2, -3, -1, 0]
     * 我们发现累积和中没有数字等于k，但是我们知道这个例子的答案是[-1, 2]，那么我们看累积和数组的第三和第四个数字，
     * 我们是否能看出一些规律呢，没错，第四个数字0减去k，得到第三个数字，这就是规律，这也是累积和求区间和的方法，
     * 但是由于累计和数组中可能会有重复数字，而哈希表的关键字不能相同，比如下面这个例子：
     * nums: [1, 0, -1], k = -1
     * sums: [1, 1, 0]
     * 我们不需要另外创建一个累积和的数组，而是直接用一个变量sum边累加边处理，
     * 而且我们哈希表也完全不用建立和一维数组的映射，只要保存第一个出现该累积和的位置，
     * 后面再出现直接跳过，这样算下来就是最长的子数组
     */
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i] + sum;
            if (!dict.containsKey(sum)) {
                dict.put(sum, i);
            }
            if (sum == k) {
                max = Math.max(max, i + 1);
            }
            int diff = sum - k;
            if (dict.containsKey(diff)) {
                max = Math.max(max, i - dict.get(diff));
            }
        }
        return max;
    }
}
