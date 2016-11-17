package LeetCode;

import Interface.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * ...1
 * ./...\
 * 2.....3
 * .\
 * ..5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 * 这个主要就是实现题。对树进行深度遍历，在遍历的过程中保存访问节点，当遍历到叶节点的时候，打印出来路径即可。
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        LinkedList<Integer> candidate = new LinkedList<>();
        if (root != null)
            btp(root, candidate, ans);
        return ans;
    }

    private void btp(TreeNode root, LinkedList<Integer> candidate, List<String> ans) {
        candidate.offerLast(root.val);
        if (root.left == null && root.right == null) {
            ans.add(candidate.stream().map(e -> String.valueOf(e)).collect(Collectors.joining("->")));
        }
        if (root.left != null)
            btp(root.left, candidate, ans);
        if (root.right != null)
            btp(root.right, candidate, ans);
        candidate.pollLast();
    }
}
