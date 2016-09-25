package LeetCode;

import Interface.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on
 * how your serialization/deserialization algorithm should work. You just need to ensure that a
 * binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * .    1
 * .   / \
 * .  2   3
 * .     / \
 * .    4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Print the tree in pre-order traversal and use "X" to denote null node and split node with ",".
 * We can use a StringBuilder for building the string on the fly.
 * For de-serializing, we use a Queue to store the pre-order traversal and since we have "X" as null node,
 * we know exactly how to where to end building sub-tress.
 */
public class SerializeDeserializeBinaryTree {
    private static final String splitter = ",";
    private static final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(splitter);
        } else {
            sb.append(node.val).append(splitter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(splitter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NULL)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
