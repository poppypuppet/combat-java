package LeetCode;

public class SingleNumber {

    /**
     * Given an array of numbers nums, in which exactly two elements appear only once
     * and all the other elements appear exactly twice. Find the two elements that appear only once.
     * <p>
     * For example:
     * <p>
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     * <p>
     * Note:
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity.
     * Could you implement it using only constant space complexity?
     * 这道题其实是很巧妙的利用了Single Number 单独的数字的解法,因为那道解法是可以准确的找出只出现了一次的数字,
     * 但前提是其他数字必须出现两次才行.而这题有两个数字都只出现了一次,那么我们如果能想办法把原数组分为两个小数组,
     * 不相同的两个数字分别在两个小数组中,这样分别调用Single Number 单独的数字的解法就可以得到答案.
     * 那么如何实现呢,首先我们先把原数组全部异或起来,那么我们会得到一个数字,这个数字是两个不相同的数字异或的结果,
     * 我们取出其中任意一位为'1'的位,为了方便起见,我们用 a &= -a 来取出最右端为'1'的位,然后和原数组中的数字挨个相与,
     * 那么我们要求的两个不同的数字就被分到了两个小组中,分别将两个小组中的数字都异或起来,就可以得到最终结果了.
     */
    public int[] singleNumberIII(int[] nums) {
        int separator = nums[0];
        for (int i = 1; i < nums.length; i++) {
            separator ^= nums[i];
        }
        //我们用 a &= -a 来取出最右端为‘1’的位
        separator &= -separator;
        int one = 0;
        int two = 0;
        for (int num : nums) {
            if ((num & separator) == 0) {
                one ^= num;
            } else {
                two ^= num;
            }
        }
        return new int[]{one, two};
    }
}
