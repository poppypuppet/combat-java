package LinkedIn;

import Interface.TreeNode;

public class BinaryTreeEquality {
    public boolean compare(TreeNode a, TreeNode b) {
        if (a == b) {
            // both null, or the same object
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (a.equals(b.val) && compare(a.left, b.left) && compare(a.right, b.right));
    }
}
