package LeetCode;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
public class LongestPalindromicSubstring {
    public static void main(String argv[]) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome("bb"));
    }

    /**
     * DP解法 space=O(n^2) time=O(n^2)
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int left = 0;
        int right = 0;
        int max = 0;
        int[][] palindrome = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || palindrome[i + 1][j - 1] > 0)) {
                    int len = j - i + 1;
                    palindrome[i][j] = len;
                    if (max < len) {
                        max = len;
                        left = i;
                        right = j;
                    }
                }
            }
        }
        return s.substring(left, right + 1);
    }

    /** O(n)解法
     * */
}
