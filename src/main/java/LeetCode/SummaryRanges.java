package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n > 0) {
            int start = nums[0];
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[i - 1] + 1) {
                    if (start == nums[i - 1]) {
                        ans.add(String.valueOf(start));
                    } else {
                        ans.add(String.format("%d->%d", start, nums[i - 1]));
                    }
                    start = nums[i];
                }
            }
            if (nums[n - 1] != start) {
                // 上次没有推入答案
                String str = String.format("%d->%d", start, nums[n - 1]);
                ans.add(str);
            } else {
                // 已经推入答案,最后剩下一个单数
                ans.add(String.valueOf(nums[n - 1]));
            }
        }
        return ans;
    }
}
