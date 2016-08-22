package LeetCode;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 */
public class CoinChange {
    /**
     * 动态规划
     * dp，设dp[i] 为兑换目标i最少的硬币数。
     * 则有：dp[i + coins[j] ] = min(dp[i + coins[j] ] , dp[i] + 1）
     * 说白了就是用当前的硬币能组合成啥，取最小。
     * dp[i] = min(dp[i], dp[i - coins[j]] + 1);
     * 其中coins[j]为第j个硬币，而i - coins[j]为钱数i减去其中一个硬币的值，剩余的钱数在dp数组中找到值，
     * 然后加1和当前dp数组中的值做比较，取较小的那个更新dp数组。
     */
    public static void main(String[] argv) {
        CoinChange c = new CoinChange();
        int[] coins = {2};
        System.out.println(c.coinChangeSelf(coins, 3));
    }

    public int coinChangeSelf(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if ((long) i + (long) coins[j] <= (long) amount) {
                    dp[i + coins[j]] = (int) Math.min((long) dp[i + coins[j]], (long) dp[i] + 1);
                }
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
}
