package LeetCode;

import java.util.Arrays;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in
 * total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, his h-index is 3.
 * <p>
 * 1、将其发表的所有SCI论文按被引次数从高到低排序；
 * 2、从前往后查找排序后的列表,直到某篇论文的序号大于该论文被引次数.所得序号减一即为H指数.
 */
public class HIndex {
    public static void main(String[] argv) {
        HIndex h = new HIndex();
        int[] citations = {0};
        System.out.println(h.hIndex(citations));
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (i >= citations[n - i - 1]) {
                return i;
            }
        }
        return n;
    }
}
