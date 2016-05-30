package LinkedIn;

import java.util.HashSet;
import java.util.Set;

public class Palindromes {
    public static void main(String[] argv) {
        Palindromes p = new Palindromes();
        System.out.println(p.findContinuesPalindromes("abcbarra"));
    }

    public Set<String> findContinuesPalindromes(String s) {
        Set<String> ans = new HashSet<>();
        int n = s.length();
        boolean[][] palindromes = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                palindromes[i][j] = s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || palindromes[i + 1][j - 1]);
                System.out.println(i + " " + j + " " + palindromes[i][j] + " " + s.substring(i, j));
                if (palindromes[i][j]) {
                    ans.add(s.substring(i, j + 1));
                }
            }
        }
        return ans;
    }

    /**
     * Returns the set of all sub-sequences of s which are palindromes.
     * For example, given the string "abcbrta", you should return "a", "abba", "abcba", "aca",
     */
    public Set<String> findPalindromew(String s) {
        return null;
    }
}
