package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations {
    public static void main(String[] argv) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> line = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();
        combinations(n, k, 1, line, ans);
        return ans;
    }

    private void combinations(int n, int k, int start, LinkedList<Integer> line, List<List<Integer>> ans) {
        if (line.size() == k) {
            ans.add(new ArrayList<>(line));
        } else {
            for (int i = start; i <= n; i++) {
                line.offerLast(i);
                combinations(n, k, i + 1, line, ans);
                line.pollLast();
            }
        }
    }
}
