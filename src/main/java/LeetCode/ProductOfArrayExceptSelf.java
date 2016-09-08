package LeetCode;

public class ProductOfArrayExceptSelf {
    /**
     * Given an array of n integers where n > 1, nums,
     * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * Solve it without division and in O(n).
     * For example, given [1,2,3,4], return [24,12,8,6].
     * 先从前往后乘一遍,再从后往前乘一边.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] front = new int[n];
        front[0] = 1;
        for (int i = 1; i < n; i++) {
            front[i] = front[i - 1] * nums[i - 1];
        }
        int[] back = new int[n];
        back[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            back[i] = back[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n - 1; i++) {
            front[i] *= back[i];
        }
        return front;
    }
}
