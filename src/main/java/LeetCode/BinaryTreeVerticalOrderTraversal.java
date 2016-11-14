package LeetCode;

import Interface.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * Examples:
 * Given binary tree [3,9,20,null,null,15,7],
 * ...3
 * ../\
 * ./..\
 * .9..20
 * ..../\
 * .../..\
 * ..15...7
 * return its vertical order traversal as:
 * [
 * ..[9],
 * ..[3,15],
 * ..[20],
 * ..[7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7],
 * .....3
 * ..../\
 * .../..\
 * ...9...8
 * ../\../\
 * ./..\/..\
 * .4..01...7
 * return its vertical order traversal as:
 * [
 * ..[4],
 * ..[9],
 * ..[3,0,1],
 * ..[8],
 * ..[7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 * .....3
 * ..../\
 * .../..\
 * ...9...8
 * ../\../\
 * ./..\/..\
 * .4..01...7
 * ..../\
 * .../..\
 * ...5...2
 * return its vertical order traversal as:
 * [
 * ..[4],
 * ..[9,5],
 * ..[3,0,1],
 * ..[8,2],
 * ..[7]
 * ]
 */
public class BinaryTreeVerticalOrderTraversal {
    /**
     * 这道题让我们竖直遍历二叉树，并把每一列存入一个二维数组，
     * 我们看题目中给的第一个例子，3和15属于同一列，3在前，第二个例子中，3,5,2在同一列，3在前，5和2紧随其后，
     * 那么我们隐约的可以感觉到好像是一种层序遍历的前后顺序，那么我们如何来确定列的顺序呢，
     * 我们可以把根节点给个序号0，然后开始层序遍历，凡是左子节点则序号减1，右子节点序号加1，
     * 这样我们可以通过序号来把相同列的节点值放到一起，我们用一个map来建立序号和其对应的节点值的映射，
     * 用map的另一个好处是其自动排序功能可以让我们的列从左到右，由于层序遍历需要用到queue，
     * 我们此时queue里不能只存节点，而是要存序号和节点组成的pair，这样我们每次取出就可以操作序号,
     * 而且排入队中的节点也赋上其正确的序号
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        HashMap<Integer, List<Integer>> ans = new HashMap<>();
        LinkedList<Vertical> queue = new LinkedList<>();
        if (root != null) {
            queue.offerLast(new Vertical(root, 0));
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            ArrayList<Vertical> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Vertical v = queue.pollFirst();
                if (!ans.containsKey(v.offset)) {
                    ans.put(v.offset, new LinkedList<>());
                }
                ans.get(v.offset).add(v.n.val);

                if (v.n.left != null) {
                    level.add(new Vertical(v.n.left, v.offset - 1));
                }
                if (v.n.right != null) {
                    level.add(new Vertical(v.n.right, v.offset + 1));
                }
                //System.out.println(level);
            }
            queue.addAll(level);
        }
        //System.out.println(ans);
        return ans.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).
                map(e -> e.getValue()).collect(Collectors.toList());
    }

    public class Vertical {
        public int offset;
        public TreeNode n;

        public Vertical(TreeNode n, int offset) {
            this.n = n;
            this.offset = offset;
        }

        public String toString() {
            return String.format("[%d,%d]", n.val, offset);
        }
    }
}
