package LeetCode;

import Interface.TreeNode;

/**
 * Binary Tree Maximum Path Sum QuestionEditorial Solution My Submissions
 * Total Accepted: 75456
 * Total Submissions: 309743
 * Difficulty: Hard
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
 * Show Company Tags
 * Show Tags
 * Show Similar Problems
 */
public class BinaryTreeMaximumPathSum {
    private Integer max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMax(root);
        return max;
    }

    public int findMax(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = findMax(root.left); // 左子树最大和
        int right = findMax(root.right);//右子树最大和
        int maxEndRoot = left + root.val + right;//左子树-根-右子树最大和,到根节点就不再往上走了.

        // maxCrossRoot表示通过root节点能走到root的parent的最大和
        // 这个值作为返回对象返给调用父函数只有3中情况:
        // 1 从左子树到root再到parent
        // 2 从右子树到root再到parent
        // 3 直接从root到parent
        // 注意maxEndRoot那条路是不可能走到parent，因为那条路已经经过root了
        int maxCrossRoot = Math.max(root.val, root.val + Math.max(left, right));
        max = Math.max(max, Math.max(maxCrossRoot, maxEndRoot));
        // 明确递归函数的返回值是什么：这本题中返回值表示通过root节点能走到root的parent的最大和，这个值作为返回对象返给调用父函数
        return maxCrossRoot;
    }
}
