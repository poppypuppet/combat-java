package LeetCode;

import Interface.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == sum;
        } else {
            return hasPathSum(root.right, sum - root.val) || hasPathSum(root.left, sum - root.val);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> solution = new LinkedList<>();
        pathSum(result, solution, root, sum);
        return result;
    }

    private void pathSum(List<List<Integer>> result, LinkedList<Integer> solution, TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        int target = sum - root.val;
        if (root.left == null && root.right == null && target == 0) {
            solution.offerLast(root.val);
            result.add(new ArrayList<>(solution));
            solution.pollLast();
            return;
        }
        solution.offerLast(root.val);
        pathSum(result, solution, root.left, target);
        pathSum(result, solution, root.right, target);
        solution.pollLast();
    }
}
