package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    private static Map<Character, char[]> map = new HashMap<>();

    static {
        map.put('0', new char[]{});
        map.put('1', new char[]{});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits != null && digits.length() > 0) {
            letterCombinations(digits, 0, new StringBuilder(), ans);
        }
        return ans;
    }

    public void letterCombinations(String digits, int start, StringBuilder candidate, List<String> ans) {
        if (start >= digits.length()) {
            ans.add(candidate.toString());
            return;
        }

        char[] letters = map.get(digits.charAt(start));
        for (char c : letters) {
            candidate.append(c);
            letterCombinations(digits, start + 1, candidate, ans);
            candidate.setLength(candidate.length() - 1);
        }
    }
}
