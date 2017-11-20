package LeetCode;

public class MaximumProductSubarray {

    /**
     * Maximum Product Sub-array
     * Find the contiguous sub-array within an array (containing at least one number) which has the largest product.
     * For example, given the array [2,3,-2,4],
     * the contiguous sub-array [2,3] has the largest product=6.
     * 一维动态规划中的“局部最优VS全局最优法”.
     * 这里的区别是维护一个局部最优不足以求得后面的全局最优,这是由于乘法的性质不像加法那样,累加结果只要是正的一定是递增,
     * 乘法中有可能现在看起来小的一个负数,后面跟另一个负数相乘就会得到最大的乘积.
     * 不过事实上也没有麻烦很多,我们只需要在维护一个局部最大的同时,在维护一个局部最小.
     * 这样如果下一个元素遇到负数时,就有可能与这个最小相乘得到当前最大的乘积和,这也是利用乘法的性质得到的.
     * nums[i]>0:max=max(nums[i],max*nums[i]);min=min(nums[i],min*nums[i]);
     * nums[i]<0:max=max(nums[i],min*nums[i]);min=min(nums[i],max*nums[i]);
     * global=max(max,global);
     */
    int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int premax = max;
            max = Integer.max(nums[i], Integer.max(premax * nums[i], min * nums[i]));
            min = Integer.min(nums[i], Integer.min(premax * nums[i], min * nums[i]));
            global = Integer.max(max, global);
        }
        return global;
    }
}
