package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * time O(n^2), space O(1)
 */
public class PalindromePartitioning {
    public static void main(String[] argv) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("abbab"));
        System.out.println();
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        int len = s.length();
        Boolean[][] palindrome = new Boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                palindrome[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || palindrome[i + 1][j - 1]);
            }
        }
        LinkedList<String> line = new LinkedList<>();
        partitionDFS(s, 0, len, palindrome, line, ans);
        return ans;
    }

    void partitionDFS(String s, int start, int end, Boolean[][] palindrome,
                      LinkedList<String> line, List<List<String>> ans) {
        System.out.println(line);
        if (start < end) {
            // substr
            // beginIndex -- the begin index, inclusive.
            // endIndex -- the end index, exclusive.
            for (int j = start; j < end; j++) {
                if (palindrome[start][j]) {
                    line.offerLast(s.substring(start, j + 1));
                    partitionDFS(s, j + 1, end, palindrome, line, ans);
                    line.pollLast();
                }
            }
        } else if (start == end) {
            ans.add(new LinkedList<String>(line));
        }
    }
}
