package LinkedIn;

import java.util.Arrays;

/**
 * Intended Audience: All
 * Question Description:
 * Write a function that, given a list of integers (both positive and negative)
 * returns the sum of the contiguous sub-sequence with maximum sum.
 * Thus, given the sequence (1, 2, 4, 1, 3, 2, 3, 1), it should return 5.
 * Followup questions:
 * What if you were looking for the maximum product rather than the maximum sum?
 * (Moderate experience or higher only)
 * What if the list was an infinite stream, and you needed to report the maximum sum/product so far?
 * (IE each list element may have multiple 'next' elements, and there may be loops)
 * What if you were given a directed graph
 * Take in a goal value and return true if you can find a contiguous sum/product with that value,and false otherwise.
 * Sum is probably more difficult than product in this case.
 * Interviewer tips: Solutions to both should be linear, ideally single pass for both.
 * Be prepared to provide sample inputs to help candidates out, particularly in cases like all negative lists.
 * Be prepared to remind candidates that we only care about the sum, not which portion of the sequence gave that sum.
 */
public class MaxContiguousSequence {
    public static void main(String[] argv) {
        int[] nums = {-4, -3};
        Arrays.stream(nums).forEach(i -> System.out.println(i));
        MaxContiguousSequence max = new MaxContiguousSequence();
        System.out.println(max.maxProduct(nums));
    }

    /**
     * 维护一个局部最差
     * local_min[i] = min(local[i-1]*nums[i], nums[i]);
     * local_max[i] = max(local[i-1]*nums[i], nums[i]); //这是对的
     * global[i] = max(local[i], global[i-1]);
     */
    public int maxProduct(int[] nums) {
        int local_min = nums[0];
        int local_max = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            System.out.println(local_max + " " + local_min + " " + global);
            int pre_local_min = local_min;
            local_min = nums[i] > 0 ? Math.min(local_min * nums[i], nums[i]) : Math.min(local_max * nums[i], nums[i]);
            local_max = nums[i] > 0 ? Math.max(local_max * nums[i], nums[i]) : Math.max(pre_local_min * nums[i], nums[i]);
            global = Math.max(global, local_max);
        }
        System.out.println(local_max + " " + local_min + " " + global);
        return global;
    }

    /**
     * 一个是局部最优,就是必须包含当前元素(以当前元素结尾)的最优的解,一个是全局最优,就是到当前元素为止最优的解是.
     * local[i] = max(global[i-1]+nums[i], nums[i]); //这是错的
     * local[i] = max(local[i-1]+nums[i], nums[i]); //这是对的
     * global[i] = max(local[i], global[i-1]);
     */
    public int maxSubArray(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int local = nums[0];
        int global = local > 0 ? local : 0; // 不一定包含
        for (int i = 1; i < nums.length; i++) {
            local = Math.max(local + nums[i], nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }

    /**
     * Maximum sum such that no two elements are adjacent
     * excl: 不包含前一个元素的最大和
     * incl: 包含前一个元素的最大和
     */
    int maxSumAdjacent(int[] nums) {
        return 0;
    }
}
