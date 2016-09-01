package LeetCode;

import java.util.Arrays;
import java.util.OptionalInt;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; i++) {
            ans ^= i;
            ans ^= nums[i];
        }
        return ans;
    }

    public int missingNumber_lambda(int[] nums) {
        int n = nums.length;
        OptionalInt lambda = Arrays.stream(nums).reduce((a, b) -> a ^ b);
        int ans = lambda.getAsInt();
        for (int i = 0; i <= n; i++) {
            ans ^= i;
        }
        return ans;
    }
}
