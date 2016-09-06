package LeetCode;

import java.util.LinkedList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public static void main(String[] argv) {
        DifferentWaysToAddParentheses dwap = new DifferentWaysToAddParentheses();
        dwap.diffWaysToCompute("2-1-1");
    }

    /**
     * 这题就是分治法 - Divide and Conquer
     * 在递归的过程中，根据符号位，不断将一个字符串分成两个子串，
     * 然后将两个子串的结果merge起来。
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int n : left) {
                    for (int m : right) {
                        switch (ch) {
                            case '+':
                                ans.add(n + m);
                                break;
                            case '-':
                                ans.add(n - m);
                                break;
                            case '*':
                                ans.add(n * m);
                                break;
                        }
                    }
                }
            }
        }
        if (ans.isEmpty()) {
            ans.add(Integer.valueOf(input));
        }
        return ans;
    }
}
