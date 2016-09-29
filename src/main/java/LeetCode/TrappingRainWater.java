package LeetCode;

public class TrappingRainWater {
    /**
     * two pointers
     * 对任意位置i，在i上的积水，由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。
     * 定义Hmin = min(A[left], A[right])，则积水量Si为：
     * Hmin <= A[i]时，Si = 0
     * Hmin > A[i]时，Si = Hmin - A[i]
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int[] lefts = new int[n];
        int[] rights = new int[n];

        for (int i = 1; i < n; i++) {
            lefts[i] = Math.max(lefts[i - 1], height[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rights[i] = Math.max(rights[i + 1], height[i + 1]);
        }
        int water = 0;
        for (int i = 0; i < n; i++) {
            int min = Math.min(lefts[i], rights[i]);
            if (min > height[i]) {
                water += min - height[i];
            }
        }
        return water;
    }
}
