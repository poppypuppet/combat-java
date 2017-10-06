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
     * 定义函数
     * P[i,j] = 字符串区间[i,j]是否为palindrome.
     * <p>
     * 首先找个例子，比如S="abccb",
     * S =     a  b  c  c  b
     * Index = 0  1  2  3  4
     * <p>
     * P[0,0] = 1  //each char is a palindrome
     * <p>
     * P[0,1] = S[0] == S[1]
     * P[1,1] = 1
     * <p>
     * P[0,2] = S[0] == S[2] && P[1,1]
     * P[1,2] = S[1] == S[2]
     * P[2,2] = 1
     * <p>
     * P[0,3] = S[0] == S[3] && P[1,2]
     * P[1,3] = S[1] == S[3] && P[2,2]
     * P[2,3] = S[2] == S[3]
     * P[3,3] = 1
     * <p>
     * 由此就可以推导出规律
     * P[i,j] =
     * if i = j,   1
     * if j = i+1, S[i] == S[j]
     * if j > i+1, S[i] == S[j] && P[i+1][j-1]
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
