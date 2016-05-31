package LinkedIn;

import java.util.HashMap;

/**
 * Given a collection T of characters and a string S, find the minimum window in S which will contain all the characters in
 * T
 * For example,
 * S = “ADOBECODEBANC”
 * T = “ABC”
 * The minimum window is “BANC”.
 */
public class MinimumWindowSubstring {

    public static void main(String argv[]) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> records = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int count = 0;
        int all = t.length();

        String res = null;
        for (char c : t.toCharArray()) {
            records.put(c, -1);
        }
        System.out.println(records.toString());
        int begin = 0, end = 0;
        int n = s.length();
        while (begin < n && end < n) {
            char c = s.charAt(end);
            System.out.println(String.format("begin=%d, end=%d, c=%c", begin, end, c));
            if (records.containsKey(c)) {
                int record = records.get(c);
                if (record == -1) {
                    records.put(c, end);
                    count++;
                } else {
                    begin = record + 1;
                    while (!records.containsKey(s.charAt(begin))) {
                        begin++;
                    }
                    for (char r : records.keySet()) {
                        if (records.get(r) < begin) {
                            records.put(r, -1);
                            count--;
                        }
                    }
                    records.put(c, end);
                    count--;
                }
            }
            System.out.println(records.toString());
            System.out.println(String.format("count=%d, all=%d", count, all));
            if (count == all) {
                if (end - begin + 1 <= min) {
                    res = s.substring(begin, end + 1);
                }
            }
            end++;
        }
        if (count == all) {
            if (end - begin + 1 <= min) {
                res = s.substring(begin, end + 1);
            }
        }
        return res;
    }
}
