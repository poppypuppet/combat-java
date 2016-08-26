package LinkedIn;

import Interface.TreeNode;

import java.io.PrintStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class TreePrinter {
    /**
     * Queue使用时要尽量避免Collection的add()和remove()方法,而是要使用offer()来加入元素,使用poll()来获取并移出元素.
     * 它们的优点是通过返回值可以判断成功与否,add()和remove()方法在失败的时候会抛出异常.
     * 如果要使用前端而不移出该元素,使用element()或者peek()方法.
     */

    public static void printByLevel(PrintStream out, TreeNode root) {
        Queue<SimpleEntry<TreeNode, Integer>> q = new LinkedList<>();
        q.add(new SimpleEntry(root, 0));
        int curr = 0;
        while (!q.isEmpty()) {
            SimpleEntry<TreeNode, Integer> e = q.poll();
            TreeNode n = e.getKey();
            int level = e.getValue();
            if (curr < level) {
                out.println();
                curr = level;
            }
            out.print(n.val);
            if (n.left != null) {
                q.offer(new SimpleEntry(n.left, curr + 1));
            }
            if (n.right != null) {
                q.offer(new SimpleEntry(n.right, curr + 1));
            }
        }
        out.println("==========");
    }

    public static void printByZigzag(PrintStream out, TreeNode root) {
        Queue<SimpleEntry<TreeNode, Integer>> q = new LinkedList<>();
        ArrayList<Integer> level = new ArrayList<>();
        q.add(new SimpleEntry(root, 0));
        int curr_level = 0;
        boolean reverse = false;
        while (!q.isEmpty()) {
            SimpleEntry<TreeNode, Integer> e = q.poll();
            TreeNode treeNode = e.getKey();
            int node_level = e.getValue();
            if (curr_level < node_level) {
                out.println();
                curr_level = node_level;
                if (reverse) {
                    Collections.reverse(level);
                }
                out.println(level.toString());
                reverse = !reverse;
                level.clear();
            }
            level.add(treeNode.val);
            if (treeNode.left != null) {
                q.offer(new SimpleEntry(treeNode.left, curr_level + 1));
            }
            if (treeNode.right != null) {
                q.offer(new SimpleEntry(treeNode.right, curr_level + 1));
            }
        }
        if (reverse) {
            Collections.reverse(level);
        }
        out.println(level.toString());
        out.println("==========");
    }
}
