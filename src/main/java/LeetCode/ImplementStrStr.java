package LeetCode;

/**
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Subscribe to see which companies asked this question
 */
public class ImplementStrStr {
    public static void main(String[] argv) {
        ImplementStrStr s = new ImplementStrStr();
        System.out.println(s.strStr("a", "a"));
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (haystack == null || "".equals(haystack)) {
            return -1;
        }

        int len = needle.length();
        for (int i = 0; i < haystack.length() - len + 1; i++) {
            System.out.println(haystack.substring(i, i + len));
            if (needle.equals(haystack.substring(i, i + len))) {
                return i;
            }
        }
        return -1;
    }
}
