package LeetCode;

import Interface.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 复杂度
 * 时间 O(b^(h+1)-1) 空间 O(h) 递归栈空间 对于二叉树b=2
 * 思路
 * 后序遍历就不能简单的改变pop顺序来实现了，我们知道根节点（这里的根节点不是整个树的根，而是相对于左右节点的跟）
 * 是在左右节点都计算完才计算的，所以我们会遇到两次根节点，第一次遇到根节点时我们将左右节点加入栈，但不把根节点pop出去，
 * 等到处理完左右节点后，我们又会遇到一次根节点，这时再计算根节点并把它pop出去。为了判断是第一次还是第二次遇到这个根节点，
 * 我们可以用一个数据结构把这个信息封装进去，第一次遇到的时候将其设为已经访问了一次，
 * 这样第二次遇到时发现已经访问了一次，就可以直接pop了。
 */
public class BinaryTreePostOrderTraversal {
    // left->right->root
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<PowerNode> stack = new Stack<>();
        if (root != null) {
            stack.add(new PowerNode(root, false));
        }
        while (!stack.empty()) {
            PowerNode pn = stack.peek();
            if (pn.visited) {
                ans.add(pn.node.val);
                stack.pop();
            } else {
                pn.visited = true;
                if (pn.node.right != null) {
                    stack.add(new PowerNode(pn.node.right, false));
                }
                if (pn.node.left != null) {
                    stack.add(new PowerNode(pn.node.left, false));
                }
            }
        }
        return ans;
    }

    public class PowerNode {
        public TreeNode node;
        public boolean visited;

        public PowerNode(TreeNode n, boolean v) {
            this.node = n;
            this.visited = v;
        }
    }
}
