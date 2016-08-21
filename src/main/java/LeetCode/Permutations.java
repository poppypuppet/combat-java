package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {
    public static void main(String[] argv) {
        int[] nums = {3, 3, 0, 3};
        Permutations p = new Permutations();
        System.out.println(p.permuteUnique(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        } else {
            LinkedList<Integer> line = new LinkedList<>();
            List<List<Integer>> ans = new LinkedList<>();
            boolean[] visited = new boolean[nums.length];
            permutation(nums, visited, line, ans);
            return ans;
        }
    }

    private void permutation(int[] nums, boolean[] visited, LinkedList<Integer> line, List<List<Integer>> ans) {
        if (line.size() == nums.length) {
            ans.add(new ArrayList<>(line));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    line.offerLast(nums[i]);
                    permutation(nums, visited, line, ans);
                    visited[i] = false;
                    line.pollLast();
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) {
            return null;
        } else {
            Arrays.sort(nums);
            LinkedList<Integer> line = new LinkedList<>();
            List<List<Integer>> ans = new LinkedList<>();
            boolean[] visited = new boolean[nums.length];
            permutationUnique(nums, visited, line, ans);
            return ans;
        }
    }

    private void permutationUnique(int[] nums, boolean[] visited, LinkedList<Integer> line, List<List<Integer>> ans) {
        if (line.size() == nums.length) {
            ans.add(new ArrayList<>(line));
        } else {
            for (int i = 0; i < nums.length; i++) {
        /*
            去除重复元素影响。
            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
            不应该让后面的2使用。
        */
                if (visited[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
                visited[i] = true;
                line.offerLast(nums[i]);
                permutationUnique(nums, visited, line, ans);
                visited[i] = false;
                line.pollLast();
            }
        }
    }
}
