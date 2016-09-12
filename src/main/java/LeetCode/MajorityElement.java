package LeetCode;

import java.util.LinkedList;
import java.util.List;


public class MajorityElement {
    /**
     * Given an integer array of size n, find all elements that appear more than⌊ n/3 ⌋ times.
     * The algorithm should run in linear time and in O(1) space.
     * 观察可知，数组中至多可能会有2个出现次数超过 ⌊ n/3 ⌋ 的众数
     * 记变量n1, n2为候选众数; c1, c2为它们对应的出现次数;
     * 遍历数组，记当前数字为num;
     * 若num与n1或n2相同，则将其对应的出现次数加1;
     * 否则，若c1或c2为0，则将其置为1，对应的候选众数置为num;
     * 否则，将c1与c2分别减1;
     * 最后，再统计一次候选众数在数组中出现的次数，若满足要求，则返回之。
     */
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int x = 0, y = 0;
        int cx = 0, cy = 0;
        for (int num : nums) {
            if (num == x) {
                // 表示这个数字的出现次数+1
                cx++;
            } else if (num == y) {
                // 表示这个数字的出现次数+1
                cy++;
            } else if (cx == 0) {
                // 领先数字已经不再领先,用这个数字替代.
                x = num;
                cx = 1;
            } else if (cy == 0) {
                // 领先数字已经不再领先,用这个数字替代.
                y = num;
                cy = 1;
            } else {
                // 表示领先数字的领先count下降
                cx--;
                cy--;
            }
        }
        // 得到了最领先的两个数字,但是不见得是大于n/3.
        cx = 0;
        cy = 0;
        for (int num : nums) {
            if (num == x) {
                cx++;
            } else if (num == y) {
                cy++;
            }
        }

        List<Integer> ans = new LinkedList<>();
        if (cx > n / 3) {
            ans.add(x);
        }
        if (cy > n / 3) {
            ans.add(y);

        }
        return ans;
    }
}
