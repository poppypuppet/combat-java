package LeetCode;

/**
 * 复杂度
 * 时间 O(NlogN) 空间 O(1)
 * 先排序,21,43,54 进行交换.
 * 复杂度
 * 时间 O(N) 空间 O(1)
 * 思路
 * 题目对摇摆排序的定义有两部分:
 * 如果i是奇数,nums[i] >= nums[i - 1]
 * 如果i是偶数,nums[i] <= nums[i - 1]
 * 所以我们只要遍历一遍数组,把不符合的情况交换一下就行了.
 * 具体来说,如果nums[i] > nums[i - 1], 则交换以后肯定有nums[i] <= nums[i - 1].
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 需要交换的情况:奇数时nums[i] < nums[i - 1]或偶数时nums[i] > nums[i - 1]
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int tmp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
