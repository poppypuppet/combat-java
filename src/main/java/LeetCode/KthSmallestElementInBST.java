package LeetCode;

import Interface.TreeNode;

import java.util.Stack;

public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pin = root;
        while (pin != null) {
            stack.add(pin);
            pin = pin.left;
        }
        int i = 0;
        while (i < k && !stack.empty()) {
            pin = stack.pop();
            i++;
            TreeNode right = pin.right;
            while (right != null) {
                stack.add(right);
                right = right.left;
            }
        }
        return pin.val;
    }
}
