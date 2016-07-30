package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Palindrome Pairs   My Submissions QuestionEditorial Solution
 * Total Accepted: 4197 Total Submissions: 21482 Difficulty: Hard
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * <p>
 * 利用字典wmap保存单词 -> 下标的键值对
 * 遍历单词列表words，记当前单词为word，下标为idx：
 * 1). 若当前单词word本身为回文，且words中存在空串，则将空串下标bidx与idx加入答案
 * 2). 若当前单词的逆序串在words中，则将逆序串下标ridx与idx加入答案
 * 3). 将当前单词word拆分为左右两半left，right。
 * 3.1) 若left为回文，并且right的逆序串在words中，则将right的逆序串下标rridx与idx加入答案
 * 3.2) 若right为回文，并且left的逆序串在words中，则将left的逆序串下标idx与rlidx加入答案
 */
public class PalindromePairs {
    public static void main(String[] argv) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        PalindromePairs pp = new PalindromePairs();
        System.out.println(pp.palindromePairs(words));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            dict.put(words[i], i);
        }
        List<List<Integer>> ans = new LinkedList<>();
        for (String word : words) {
            if (isPalindrome(word) && dict.containsKey("")) {
                if (dict.get(word) != dict.get("")) {
                    ans.add(Arrays.asList(dict.get(word), dict.get("")));
                    ans.add(Arrays.asList(dict.get(""), dict.get(word)));
                }
            }
            StringBuilder sb = new StringBuilder(word);
            String reverseWord = sb.reverse().toString();
            if (dict.containsKey(reverseWord)) {
                if (dict.get(word) != dict.get(reverseWord)) {
                    ans.add(Arrays.asList(dict.get(word), dict.get(reverseWord)));
                }
            }
            for (int i = 1; i < word.length(); i++) {
                String left = word.substring(0, i);
                String right = word.substring(i);
                sb.setLength(0); // clear
                sb.append(left);
                String rleft = sb.reverse().toString();
                sb.setLength(0); // clear
                sb.append(right);
                String rright = sb.reverse().toString();
                if (isPalindrome(left) && dict.containsKey(rright)) {
                    ans.add(Arrays.asList(dict.get(rright), dict.get(word)));
                }
                if (isPalindrome(right) && dict.containsKey(rleft)) {
                    ans.add(Arrays.asList(dict.get(word), dict.get(rleft)));
                }
            }
        }
        return ans;
    }

    public boolean isPalindrome(String word) {
        int n = word.length();
        if (n > 1) {
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                if (word.charAt(i) != word.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
