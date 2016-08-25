package LeetCode;

import Interface.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 复杂度
 * 时间 O(b^(h+1)-1) 空间 O(h) 递归栈空间 对于二叉树b=2
 * 思路
 * 用迭代法做深度优先搜索的技巧就是使用一个显式声明的Stack存储遍历到节点，替代递归中的进程栈，实际上空间复杂度还是一样的。
 * 对于先序遍历，我们pop出栈顶节点，记录它的值，然后将它的左右子节点push入栈，以此类推。
 */
public class BinaryTreePreOrderTraversal {
    // root->left->right
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> cache = new Stack<>();
        if (root != null) {
            cache.add(root);
        }
        while (!cache.empty()) {
            TreeNode n = cache.pop();
            ans.add(n.val);
            if (n.right != null) {
                cache.add(n.right);
            }
            if (n.left != null) {
                cache.add(n.left);
            }
        }
        return ans;
    }
}
