package LeetCode;

import Interface.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /**
     * 解法
     * 本题在属于二叉树遍历的经典题目，已知二叉树的两个遍历序列构造二叉树，有如下性质：
     * 若已知先序和中序，则可以构造出唯一的二叉树
     * 若已知先序和后序，则可以构造出多颗不同的二叉树
     * 若已知中序和后序，则可以构造出唯一的二叉树
     * 本题中我们已知的条件为中序遍历和后序遍历，所以我们一定可以构造出唯一的二叉树。
     * 我们先将整棵树看作根节点和两颗子树，则其两种遍历得到的序列为
     * in order
     * +------------+------+-------------+
     * | left child | root | right child |
     * +------------+------+-------------+
     * post order
     * +------------+-------------+------+
     * | left child | right child | root |
     * +------------+-------------+------+
     * 可以肯定后序遍历序列中最后一个数一定是当前二叉树的根节点root。又因为二叉树不存在相同的数，我们可以找到root在中序遍历中位置p。
     * 则我们可以分别找到两颗子树对应的中序和后序遍历：
     * 左子树的中序 = inOrder[1 .. p - 1]
     * 左子树的后序 = postOrder[1 .. p - 1]
     * 右子树的中序 = inOrder[p + 1 .. n]
     * 右子树的后序 = postOrder[p .. n - 1]
     * 在此基础上我们就可以递归处理两颗子树。
     * 当我们发现当前中序遍历和后序遍历长度都为 1 的时候，也就找到了叶子节点，此时我们开始回溯。
     * 具体的实现可以参考下面代码。
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }
        int rootVal = postorder[postend];
        TreeNode root = new TreeNode(rootVal);
        int position = findPos(inorder, instart, inend, rootVal);
        root.left = buildTree(inorder, instart, position - 1, postorder, poststart, poststart + position - instart - 1);
        root.right = buildTree(inorder, position + 1, inend, postorder, poststart + position - instart, postend - 1);
        return root;
    }

    private int findPos(int[] arr, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
