package LeetCode;

public class MaximumSubarray {
    /**
     * Maximum Subarray
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4].
     * The contiguous subarray [4,−1,2,1] has the largest sum=6.
     * <p>
     * 局部最优 VS 全局最优法
     * 在每一步,我们维护两个变量,一个是全局最优,就是到当前元素为止最优的解是,一个是局部最优,就是必须包含当前元素的最优的解.
     * 接下来说说动态规划的递推式(这是动态规划最重要的步骤,递归式出来了,基本上代码框架也就出来了).
     * 假设我们已知第i步的global[i](全局最优)和local[i](局部最优),那么第i+1步的表达式是:
     * local[i+1]=max(nums[i], local[i]+nums[i]);
     */
    public int maxSubArray(int[] nums) {
        return 0;
    }

    /**
     * Maximum Product Sub-array
     * Find the contiguous sub-array within an array (containing at least one number) which has the largest product.
     * For example, given the array [2,3,-2,4],
     * the contiguous sub-array [2,3] has the largest product=6.
     * <p>
     * 一维动态规划中的“局部最优VS全局最优法”.
     * 这里的区别是维护一个局部最优不足以求得后面的全局最优,这是由于乘法的性质不像加法那样,累加结果只要是正的一定是递增,
     * 乘法中有可能现在看起来小的一个负数,后面跟另一个负数相乘就会得到最大的乘积.
     * 不过事实上也没有麻烦很多,我们只需要在维护一个局部最大的同时,在维护一个局部最小.
     * 这样如果下一个元素遇到负数时,就有可能与这个最小相乘得到当前最大的乘积和,这也是利用乘法的性质得到的.
     */
    int maxProduct(int[] nums) {

        return 0;
    }

    /* Maximum sum such that no two elements are adjacent
    Given an array of positive numbers, find the maximum sum of a sub-sequence with the constraint that no 2 numbers in the sequence should be adjacent in the array.
    So [3,2,7,10] should return 13 (sum of 3 and 10) or [3,2,5,10,7] should return 15 (sum of 3, 5 and 7).
    解题思路:
    遍历array 中的所有元素，设置两个变量：
    excl: 不包含前一个元素的最大和
    incl: 包含前一个元素的最大和

    更新当前元素的 excl 和 incl：
    不包含当前元素的最大和 excl=max(incl’， excl’)
    包含当前元素的最大和 incl=excl’+current (元素不能相邻)

    arr[]={5,5,10,40,50,35}
    1) arr[0]=5
       incl=5
       excl=0
    2) arr[1]=5
       incl=(excl + arr[1])=5
       excl=max(5, 0)=5
    3) arr[2]=10
       incl=(excl + arr[2])=15
       excl=max(5, 5)=5
    4) arr[3]=40
       incl=(excl + arr[3])=45
       excl=max(5, 15)=15
    5) arr[4]=50
       incl=(excl + arr[4])=65
       excl=max(15, 45)=45
    6) arr[5]=35
       incl=(excl + arr[5])=80
       excl=max(45, 65)=65
    answer is max(incl, excl)=80
    */
    int maxSum(int[] nums) {
        return 0;
    }


}
