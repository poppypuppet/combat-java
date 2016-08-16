package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public static void main(String[] argv) {
        int[] nums = {1, 2, 3};
        Subsets s = new Subsets();
        List<List<Integer>> ans = s.subsets(nums);
        System.out.println(ans);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> line = new LinkedList<>();
        subSetsDfs(0, line, ans, nums);
        return ans;
    }

    private void subSetsDfs(int start, LinkedList<Integer> line, List<List<Integer>> ans, int[] nums) {
        ans.add(new ArrayList<>(line));
        for (int i = start; i < nums.length; i++) {
            line.offerLast(nums[i]);
            subSetsDfs(i + 1, line, ans, nums);
            line.pollLast();
        }
    }
}
