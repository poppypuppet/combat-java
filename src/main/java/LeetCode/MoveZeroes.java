package LeetCode;

public class MoveZeroes {
    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     */
    public void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                swap(nums, i, j++);
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
