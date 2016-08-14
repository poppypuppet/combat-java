package LeetCode;

import java.util.LinkedHashMap;

/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class RomanToInteger {
    public static LinkedHashMap<Character, Integer> romans = new LinkedHashMap<>();

    static {
        romans.put('M', 1000);
        romans.put('D', 500);
        romans.put('C', 100);
        romans.put('L', 50);
        romans.put('X', 10);
        romans.put('V', 5);
        romans.put('I', 1);
    }

    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char left = s.charAt(i);
            char right = s.charAt(i + 1);
            if (romans.get(left) >= romans.get(right)) {
                ans += romans.get(left);
            } else {
                ans -= romans.get(left);
            }
        }
        ans += romans.get(s.charAt(s.length() - 1));
        return ans;
    }
}
