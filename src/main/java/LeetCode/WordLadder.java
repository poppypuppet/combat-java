package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder {

    public Map<Set<String>, Boolean> cache = new HashMap<>();

    public static void main(String[] argv) {
        WordLadder w = new WordLadder();
        String[] wordList = {"a", "b", "c"};
        Set<String> words = new HashSet<String>(Arrays.asList(wordList));
        System.out.println(w.ladderLength("a", "c", words));
        System.out.println(w.ladderLength_double("a", "c", words));
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        LinkedList<ELE> queue = new LinkedList<>();
        HashSet<String> delete = new HashSet<>();
        queue.add(new ELE(beginWord, 1));
        while (!queue.isEmpty()) {
            ELE e = queue.pollFirst();
            if (distance(e.word, endWord)) {
                return e.distance + 1;
            } else {
                for (String word : wordList) {
                    if (distance(word, e.word) && !delete.contains(word)) {
                        queue.add(new ELE(word, e.distance + 1));
                        delete.add(word);
                    }
                }
            }
        }
        return 0;
    }

    private boolean distance(String s1, String s2) {
        HashSet<String> entry = new HashSet<>();
        entry.add(s1);
        entry.add(s2);
        if (cache.containsKey(entry)) {
            return cache.get(entry);
        } else {
            if (s1.length() != s2.length()) {
                cache.put(entry, false);
                return false;
            } else {
                int diff = 0;
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        diff++;
                    }
                }
                boolean ans = diff == 1;
                cache.put(entry, ans);
                return ans;
            }
        }
    }

    public int ladderLength_double(String beginWord, String endWord, Set<String> wordList) {
        HashSet<String> deleted = new HashSet<>();
        HashSet<String> head = new HashSet<>();
        HashSet<String> tail = new HashSet<>();
        head.add(beginWord);
        tail.add(endWord);
        int ans = 1;
        while (!head.isEmpty() && !tail.isEmpty()) {
            if (head.size() < tail.size()) {
                HashSet<String> temp = head;
                head = tail;
                tail = temp;
            }
            HashSet<String> nextHead = new HashSet<>();
            for (String word : head) {
                char[] array = word.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    char c = array[i];
                    for (char replace = 'a'; replace <= 'z'; replace++) {
                        array[i] = replace;
                        String replacedWord = new String(array);
                        if (tail.contains(replacedWord)) {
                            return ans + 1;
                        }
                        if (!deleted.contains(replacedWord) && wordList.contains(replacedWord)) {
                            nextHead.add(replacedWord);
                            deleted.add(replacedWord);
                        }
                    }
                    array[i] = c;
                }
            }
            head = nextHead;
            ans++;
        }

        return 0;
    }

    public int ladderLength_sample(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    char old = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                    }
                    chs[i] = old;
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }

    public class ELE {
        public String word;
        public Integer distance;

        public ELE(String word, Integer distance) {
            this.word = word;
            this.distance = distance;
        }
    }
}
