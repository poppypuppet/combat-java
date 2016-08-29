package LeetCode;

import java.util.Arrays;

/**
 * Perfect Squares  QuestionEditorial Solution  My Submissions
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {
    /**
     * 动态规划法
     * 时间复杂度：O(n * sqrt n)
     * 初始化将dp数组置为无穷大；令dp[y * y] = 1，其中：y * y <= n
     * 状态转移方程：
     * dp[x + y * y] = min(dp[x + y * y], dp[x] + 1)
     * 其中：dp [i] 表示凑成i所需的平方和数字的最小个数，并且 x + y * y <= n
     */
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 每个可以直接变成平方数的设为1
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        // dp[i]的平方数字+1, 1代表 j*j,又多了一个平方数
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j]);
            }
        }
        return dp[n];
    }

    public int numSquares_fast(int n) {
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        // 注意下面的!!a + !!b这个表达式，可能很多人不太理解这个的意思，
        // 其实很简单，感叹号!表示逻辑取反，那么一个正整数逻辑取反为0，再取反为1，
        // 所以用两个感叹号!!的作用就是看a和b是否为正整数，都为正整数的话返回2，只有一个是正整数的话返回1，参见代码如下：
        for (int a = 0; a * a <= n; ++a) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a > 0 ? 1 : 0) + (b > 0 ? 1 : 0);
            }
        }
        return 3;
    }
}
