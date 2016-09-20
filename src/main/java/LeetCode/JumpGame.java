package LeetCode;

import java.util.List;

public class JumpGame {
    /**
     * Jump Game I
     * <p>
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false.
     * <p>
     * 注意题目中A[i]表示的是在位置i,“最大”的跳跃距离,而并不是指在位置i只能跳A[i]的距离.
     * 所以当跳到位置i后,能达到的最大的距离至少是i+A[i].
     * 用greedy来解,记录一个当前能达到的最远距离maxIndex:
     * 1. 能跳到位置i的条件:i<=maxIndex.
     * 2. 一旦跳到i,则maxIndex = max(maxIndex, i+A[i]).
     * 3. 能跳到最后一个位置n-1的条件是:maxIndex >= n-1
     */
    Boolean canJump(List<Integer> nums) {
        return true;
    }

    /*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Your goal is to reach the last index in the minimum number of jumps.
    For example:
    Given array A = [2,3,1,1,4]
    The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
    Note: You can assume that you can always reach the last index.
    大牛这个算法的思想主要是,扫描数组（废话。。。）,以确定当前最远能覆盖的节点,放入curr.
    然后继续扫描,直到当前的路程超过了上一次算出的覆盖范围,那么更新覆盖范围,同时更新条数,因为我们是经过了多一跳才能继续前进的.
    形象地说,这个是在争取每跳最远的greedy.举个栗子.
    */
    int jump(List<Integer> nums) {
        return 0;
    }
}
