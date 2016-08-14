package LeetCode;

import java.util.*;

/**
 * Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Subscribe to see which companies asked this question
 */
public class ValidParentheses {
    public static Set<Character> lefts = new HashSet<>();
    public static Map<Character, Character> matches = new HashMap<>();

    static {
        lefts.add('(');
        lefts.add('{');
        lefts.add('[');
        matches.put(']', '[');
        matches.put('}', '{');
        matches.put(')', '(');
    }

    public boolean isValid(String s) {
        Stack<Character> cache = new Stack<>();
        for (char c : s.toCharArray()) {
            if (lefts.contains(c)) {
                cache.add(c);
            } else {
                if (!cache.empty() && cache.peek() == matches.get(c)) {
                    cache.pop();
                } else {
                    return false;
                }
            }
        }
        return cache.empty();
    }
}
