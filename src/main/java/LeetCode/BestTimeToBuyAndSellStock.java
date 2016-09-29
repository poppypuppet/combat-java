package LeetCode;

public class BestTimeToBuyAndSellStock {
    /**
     * Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction
     * (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     * 贪心法,分别找到价格最低和最高的一天,低进高出,注意最低的一天要在最高的一天之前。
     * 把原始价格序列变成差分序列,本题也可以做是最大m子段和,m = 1。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            // 现在可能的利润=现在的值-从前的最小值
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
     * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * 贪心法,低进高出,把所有正的价格差价相加起来.把原始价格序列变成差分序列,本题也可以做是最大m子段和,m=数组长度。
     */
    public int maxProfitII(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * 解法:
     * 设状态f(i),表示区间[0,i](0≤i≤n−1)的最大利润,
     * 状态g(i),表示区间[i,n−1](0≤i≤n−1)的最大利润.
     * 最终答案为 max {f (i) + g(i)}, 0 ≤ i ≤ n − 1.
     * 注意:
     * 允许在一天内买进又卖出,相当于不交易,因为题目的规定是最多两次,而不是一定要两次.
     */
    public int maxProfitIII(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int[] fronts = new int[n];
        int[] backs = new int[n];
        int min = prices[0];
        int max = prices[n - 1];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            fronts[i] = Math.max(fronts[i - 1], prices[i] - min);
            int j = n - i - 1;
            max = Math.max(max, prices[j]);
            backs[j] = Math.max(backs[j + 1], max - prices[j]);
        }
        int profit = 0;
        for (int i = 0; i < n; i++) {
            profit = Math.max(profit, fronts[i] + backs[i]);
        }
        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     * 特殊动态规划法.
     * 传统的动态规划我们会这样想.到第i天时进行j次交易的最大收益.
     * 要么等于到第i-1天时进行j次交易的最大收益(第i天价格低于第i-1天的价格).
     * 要么等于到第i-1天时进行j-1次交易.然后第i天进行一次交易(第i天价格高于第i-1天价格时).
     * 于是得到动规方程如下(其中diff = prices[i] - prices[i - 1]):
     * profit[i][j] = max(profit[i - 1][j], profit[i - 1][j - 1] + diff)
     * 看起来很有道理.但其实不对.为什么不对呢？因为diff是第i天和第i-1天的差额收益.
     * 如果第i-1天当天本身也有交易呢(也就是说第i-1天刚卖出了股票.然后又买入等到第i天再卖出)
     * 那么这两次交易就可以合为一次交易.这样profit[i - 1][j - 1] + diff实际上只进行了j-1次交易.
     * 而不是最多可以的j次.这样得到的最大收益就小了.
     * <p>
     * 那么怎样计算第i天进行交易的情况的最大收益.才会避免少计算一次交易呢？
     * 我们用一个局部最优解和全局最有解表示到第i天进行j次的收益.这就是该动态规划的特殊之处.
     * 用local[i][j]表示到达第i天时.最多进行j次交易的局部最优解,i天必须交易.
     * 用global[i][j]表示到达第i天时.最多进行j次交易的全局最优解.
     * 它们二者的关系如下(其中diff = prices[i] - prices[i - 1]):
     * local[i][j] = max(local[i - 1][j] + diff,global[i - 1][j - 1])i
     * global[i][j] = max(global[i - 1][j],local[i][j])
     * <p>
     * local[i][j]和global[i][j]的区别是:local[i][j]意味着在第i天一定有交易(卖出)发生.
     * 当第i天的价格高于第i-1天(即diff > 0)时.那么可以把这次交易(第i-1天买入第i天卖出)跟第i-1天的交易(卖出)合并为一次交易.
     * local[i][j]=local[i-1][j]+diff;
     * 当第i天的价格不高于第i-1天(即diff<=0)时.那么local[i][j]=global[i-1][j-1]+diff.而由于diff<=0.所以可写成
     * local[i][j]=global[i-1][j-1].
     * global[i][j]就是我们所求的前i天最多进行k次交易的最大收益.
     * 可分为两种情况:
     * 如果第i天没有交易(卖出).那么global[i][j]=global[i-1][j];
     * 如果第i天有交易(卖出).那么global[i][j]=local[i][j].
     * <p>
     * 但这道题还有个坑.就是如果k的值远大于prices的天数.比如k是好几百万.而prices的天数就为若干天的话.上面的DP解法就非常的没有效率.
     * 应该直接用Best Time to Buy and Sell Stock II 买股票的最佳时间之二的方法来求解.所以实际上这道题是之前的二和三的综合体.
     * <p>
     * 我们知道,动规所用的二维辅助数组可以降为一维的,即只用大小为k的一维数组记录到达第i天时的局部最优解和全局最优解.
     * 需要注意的是,由于第i天时交易k次的最优解依赖于第i-1天时交易k-1次的最优解,所以数组更新应当从后往前(即从k到)更新.
     */
    public int maxProfitIV(int k, int[] prices) {
        return 0;
    }

    /**
     * Best Time to Buy and Sell Stock with Cool down
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     * <p>
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     * <p>
     * prices = [1, 2, 3, 0, 2]
     * maxProfitCool = 3
     * transactions = [buy, sell, cooldown, buy, sell]
     * <p>
     * 而这道题与上面这些不同之处在于加入了一个冷冻期cooldown之说,就是如果某天卖了股票,那么第二天不能买股票,有一天的冷冻期.
     * 此题需要维护三个一维数组buy,sell,rest.其中:
     * buy[i]表示在第i天之前最后一个操作是买,此时的最大收益;
     * sell[i]表示在第i天之前最后一个操作是卖,此时的最大收益;
     * rest[i]表示在第i天之前最后一个操作是冷冻期,此时的最大收益;
     * 我们写出递推式为:
     * buy[i]=max(rest[i-1]-price,buy[i-1])
     * sell[i]=max(buy[i-1]+price,sell[i-1])
     * rest[i]=max(sell[i-1],buy[i-1],rest[i-1])
     * 上述递推式很好的表示了在买之前有冷冻期,买之前要卖掉之前的股票.
     * 一个小技巧是如何保证[buy.rest.buy]的情况不会出现,
     * 这是由于buy[i]<=rest[i],即rest[i]=max(sell[i-1].rest[i-1]),这保证了[buy.rest.buy]不会出现.
     * 另外,由于冷冻期的存在,我们可以得出rest[i]=sell[i-1],这样,我们可以将上面三个递推式精简到两个:
     * buy[i]=max(sell[i-2]-price.buy[i-1])
     * sell[i]=max(buy[i-1]+price.sell[i-1])
     * 我们还可以做进一步优化,由于i只依赖于i-1和i-2,所以我们可以在O(1)的空间复杂度完成算法.
     */
    public int maxProfitCool(int[] prices) {
        return 0;
    }
}
