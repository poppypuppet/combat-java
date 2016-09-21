package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 * ["ABCE"],
 * ["SFCS"],
 * ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {
    /**
     * 就是二维的permutation
     * 基本思路是使用DFS来对一个起点字母上下左右搜索,看是不是含有给定的Word.
     * 还要维护一个visited数组,表示从当前这个元素是否已经被访问过了,过了这一轮visited要回false,
     * 因为对于下一个元素,当前这个元素也应该是可以被访问的.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;
        if (word.length() == 0)
            return true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (wordSearch(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean wordSearch(char[][] board, String word, int index, int x, int y) {
        if (word.length() == index) {
            return true;
        }
        if (x >= 0 && x < board.length && y >= 0 && y < board[x].length) {
            char c = board[x][y];
            if (Character.isAlphabetic(c) && word.charAt(index) == c) {
                board[x][y] = 0;
                if (wordSearch(board, word, index + 1, x + 1, y)) {
                    return true;
                }
                if (wordSearch(board, word, index + 1, x, y + 1)) {
                    return true;
                }
                if (wordSearch(board, word, index + 1, x - 1, y)) {
                    return true;
                }
                if (wordSearch(board, word, index + 1, x, y - 1)) {
                    return true;
                }
            }
            board[x][y] = c;
        }
        return false;
    }

    /**
     * 解题思路：
     * 将待查找的单词储存在字典树Trie中，使用DFS（深度优先搜索）在格板中查找，利用字典树剪枝。
     * 每当找到一个单词时，将该单词从字典树中删去。
     * 返回结果按照字典序递增排列。
     * 哈希表在此题中不适用，因为单词前缀的存储会消耗大量的空间。
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>(words.length);
        if (words.length < 1) {
            return ans;
        }

        TrieNode root = new TrieNode();
    }
}
