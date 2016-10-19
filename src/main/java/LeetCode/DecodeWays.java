package LeetCode;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    /**
     * 一维动态规划
     * 分析：需要注意的是，如果序列中有不能匹配的0，那么解码方法是0，
     * 比如序列012、100（第二个0可以和1组成10，第三个0不能匹配）。
     * <p>
     * 递归的解法很容易，但是大集合会超时。转换成动态规划的方法，假设dp[i]表示序列s[0...i-1]的解码数目
     * <p>
     * 动态规划方程如下：
     * dp[0]=1
     * dp[1]=(s[0]=='0')?0:1
     * dp[i]=(s[i-1]=='0'?0:dp[i-1])+(isLetter(s[i-2,i-1])?dp[i-2]:0)，
     * 第一个分量是把s[0...i-1]末尾一个数字当做一个字母来考虑，
     * 第二个分量是把s[0...i-1]末尾两个数字当做一个字母来考虑.
     * <p>
     * 动态规划来做。
     * 设置动态数组dp[n+1]。dp[i]表示从1~i的decode ways的个数。
     * 当给的code只有一位数时，判断是不是valid（A~Z），是的话就dp[1] = 1 不是的话就是dp[1] = 0
     * 因为像给的例子12可以有两种可能的解析方法，所以计算dp[i]的时候要判断两种可能性，再累加。
     * 复杂度:TimeO(n);SpaceO(n)
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = s.charAt(i - 1) == '0' ? 0 : dp[i - 1];
            int num = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            dp[i] += s.charAt(i - 2) == '0' ? 0 : (num > 0 && num < 27) ? dp[i - 2] : 0;
        }
        return dp[n];
    }
}
