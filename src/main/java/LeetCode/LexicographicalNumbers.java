package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Lexicographical Numbers  QuestionEditorial Solution  My Submissions
 * Total Accepted: 1779
 * Total Submissions: 6686
 * Difficulty: Medium
 * Given an integer n, return 1 - n in lexicographical order.
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 * 解法:
 * 想象成一棵树,左枝为*10, 右枝为+1,进行pre-order 遍历
 */
public class LexicographicalNumbers {

    public static void main(String[] argv) {
        LexicographicalNumbers l = new LexicographicalNumbers();
        System.out.println(l.lexicalOrder(13));
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        Stack<Integer> stack = new Stack<>();
        if (1 <= n) {
            stack.add(1);
        }
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            ans.add(curr);
            if (curr + 1 <= n && curr % 10 < 9) {
                stack.add(curr + 1);
            }
            if (curr * 10 <= n) {
                stack.add(curr * 10);
            }
        }
        return ans;
    }

    public List<Integer> lexicalOrderBest(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }
}
