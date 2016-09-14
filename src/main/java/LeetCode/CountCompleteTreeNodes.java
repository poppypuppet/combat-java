package LeetCode;

import Interface.TreeNode;

/**
 * 给定一棵完全二叉树，计算其中节点的个数。
 * 维基百科关于完全二叉树的定义如下：
 * <p>
 * 在一棵完全二叉树的每一层，除最后一层外，其余各层都是填满的，并且最后一层的节点尽可能的靠左排列。
 * 最后一层（第h层）包含的节点个数介于[1, 2^h]区间之内。
 */
public class CountCompleteTreeNodes {
    /**
     * 如果当前子树的“极左节点”（从根节点出发一路向左）与“极右节点”（从根节点出发一路向右）的高度h相同，
     * 则当前子树为满二叉树，返回2^h - 1
     * 否则，递归计算左子树与右子树的节点个数。
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root, right = root;
        int lh = 0, rh = 0;
        while (left != null) {
            lh++;
            left = left.left;
        }
        while (right != null) {
            rh++;
            right = right.right;
        }
        if (lh == rh) {
            return (1 << lh) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
}
