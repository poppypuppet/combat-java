package LeetCode;

import Interface.TreeNode;

import java.util.LinkedList;

/**
 * Kth Smallest Element in a BST
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * Hint:
 * Try to utilize the property of a BST.
 * What if you could modify the BST node's structure?
 * The optimal runtime complexity is O(height of BST).
 */
public class KthSmallestElementBST {
    /**
     * 采用中序遍历（左 -> 根 -> 右）即可以递增顺序访问BST中的节点,时间复杂度O(k)
     */
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pin = root;
        while (pin != null) {
            stack.offerLast(pin);
            pin = pin.left;
        }
        while (!stack.isEmpty()) {
            pin = stack.pollLast();
            k--;
            if (k == 0) {
                return pin.val;
            } else {
                TreeNode tn = pin.right;
                while (tn != null) {
                    stack.offerLast(tn);
                    tn = tn.left;
                }
            }
        }
        return -1;
    }
}
