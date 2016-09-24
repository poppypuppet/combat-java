package LeetCode;

import Interface.TrieNode;

public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (n.children[index] == null) {
                n.children[index] = new TrieNode();
            }
            n = n.children[index];
        }
        n.item = word;
    }

    // Returns if the word is in the trie.
    public TrieNode search(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (n.children[index] == null) {
                return null;
            }
            n = n.children[index];
        }
        if (word.equals(n.item)) {
            return n;
        } else {
            return null;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode n = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (n.children[index] == null) {
                return false;
            }
            n = n.children[index];
        }
        return true;
    }
}
