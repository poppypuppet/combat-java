package LeetCode;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    int initTargetHash(int[] targethash, String Target) {
        int targetnum = 0;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }

    public String minWindow(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        int[] targethash = new int[256];
        // target应该有多少个数字
        int targetnum = initTargetHash(targethash, Target);
        // source已经有多少个数字存入
        int sourcenum = 0;
        int start = 0;
        for (int end = 0; end < Source.length(); end++) {
            // targethash存在这个数字
            if (targethash[Source.charAt(end)] > 0) {
                sourcenum++;
            }
            targethash[Source.charAt(end)]--;

            while (sourcenum >= targetnum) {
                if (ans > end - start + 1) {
                    ans = Math.min(ans, end - start + 1);
                    minStr = Source.substring(start, end + 1);
                }
                targethash[Source.charAt(start)]++;
                if (targethash[Source.charAt(start)] > 0)
                    sourcenum--;
                start++;
            }
        }
        return minStr;
    }
}
