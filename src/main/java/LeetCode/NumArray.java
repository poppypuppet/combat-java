package LeetCode;

/**
 * 这道题让我们检索一个数组的某个区间的所有数字之和,题目中给了两条条件,首先数组内容不会变化,其次有很多的区间和检索.
 * 那么我们用传统的遍历相加来求每次区间和检索,十分的不高效,而且无法通过OJ.
 * 所以这道题的难点就在于是否能想到来用建立累计直方图的思想来建立一个累计和的数组dp,
 * 其中dp[i]表示[0, i]区间的数字之和,那么[i,j]就可以表示为dp[j]-dp[i-1],这里要注意一下当i=0时,直接返回dp[j]即可.
 */
public class NumArray {
    private int[] dp;

    public NumArray(int[] nums) {
        dp = nums;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    public static void main(String[] argv) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 1));
        System.out.println(numArray.sumRange(1, 2));
    }

    public int sumRange(int i, int j) {
        return i == 0 ? dp[j] : dp[j] - dp[i - 1];
    }
}


