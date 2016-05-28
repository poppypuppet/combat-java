package LinkedIn;

public class BinaryTreeEquality {
    public boolean compare(TreeNode a, TreeNode b) {
        if (a == b) { // both null, or the same object
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (a.equals(b.value) && compare(a.left, b.left) && compare(a.right, b.right));
    }
}
