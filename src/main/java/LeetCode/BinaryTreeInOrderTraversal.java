package LeetCode;

import Interface.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 复杂度
 * 时间 O(b^(h+1)-1) 空间 O(h) 递归栈空间 对于二叉树b=2
 * 思路
 * 用栈中序遍历没有先序遍历那么直观,因为我们不能马上pop出当前元素,而要先把它的左子树都遍历完才能pop它自己.
 * 所有我们先将将最左边的所有节点都push进栈,然后再依次pop并记录值,每pop一个元素后再看它有没有右子树,
 * 如果右的话,我们再将它的右节点和右子树中最左边的节点都push进栈,再依次pop.
 */
public class BinaryTreeInOrderTraversal {
    // left->root->right
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pin = root;
        while (pin != null) {
            stack.add(pin);
            pin = pin.left;
        }
        while (!stack.empty()) {
            TreeNode n = stack.pop();
            ans.add(n.val);
            // 取一次右分支
            TreeNode sub = n.right;
            while (sub != null) {
                // 所有右枝的左枝加入
                stack.add(sub);
                sub = sub.left;
            }
        }
        return ans;
    }
}
