package LinkedIn;

import java.util.*;

public class Palindromes {
    public static void main(String[] argv) {
        Palindromes p = new Palindromes();
        System.out.println(p.findContinuesPalindromes("abcbarra"));
        System.out.println(p.findPalindromes("abcbrta"));
    }

    public Set<String> findContinuesPalindromes(String s) {
        Set<String> ans = new HashSet<>();
        int n = s.length();
        boolean[][] palindromes = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                palindromes[i][j] = s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || palindromes[i + 1][j - 1]);
                System.out.println(i + " " + j + " " + palindromes[i][j] + " " + s.substring(i, j));
                if (palindromes[i][j]) {
                    ans.add(s.substring(i, j + 1));
                }
            }
        }
        return ans;
    }

    /**
     * Returns the set of all sub-sequences of s which are palindromes.
     * For example, given the string "abcbrta", you should return "a", "abba", "abcba", "aca",
     */
    public Set<String> findPalindromes(String s) {
        int len = s.length();
        // The map below will contain all palindromes sequences and the latest location of its head
        Map<String, Integer> wordLocMap = new HashMap<String, Integer>();
        // The map below will contain all characters scanned and their locations
        Map<Character, List<Integer>> charLocMap = new HashMap<Character, List<Integer>>();
        // scan the characters from left to right
        for (int i = 0; i < len; i++) {
            Character currChar = s.charAt(i);
            String single = s.substring(i, i + 1);
            List<Integer> charLocList = charLocMap.get(currChar);
            if (charLocList == null) {
                // The first time this character was met
                charLocList = new ArrayList<Integer>();
                charLocMap.put(currChar, charLocList);
                System.out.println("charLocMap=" + currChar + " " + charLocList.toString());
            } else {
                // The same character was met earlier
                // The map below will contain all the new palindromes with this new character
                Map<String, Integer> newWordLocMap = new HashMap<String, Integer>();
                for (Map.Entry<String, Integer> entry : wordLocMap.entrySet()) {
                    String oldPalindrome = entry.getKey();
                    // The location of the head of this palindrome
                    Integer headLoc = entry.getValue();
                    // a new palindrome is possible if the head of the existing palindrome
                    // is behind the first location of this character
                    System.out.println("oldPalindrome=" + oldPalindrome + " headLoc=" + headLoc);
                    if (headLoc > charLocList.get(0)) {
                        // this is a new palindrome
                        String newPalindrome = single + oldPalindrome + single;
                        // find out the latest location of head of this new palindrome
                        // It is the biggest possible location that is smaller than headLoc
                        // Binary search can be used because the location is always added from
                        // small to big to the list
                        newWordLocMap.put(newPalindrome, findLoc(charLocList, headLoc));
                        System.out.println("newWordLocMap " + newPalindrome + " " + newWordLocMap.get(newPalindrome));
                    }
                }
                // adding all new palindromes
                wordLocMap.putAll(newWordLocMap);
                // update the word location of the palindromes that has the same characters
                String sameCharString = single;
                for (int j = charLocList.size() - 1; j >= 0; j--) {
                    sameCharString = sameCharString + single;
                    wordLocMap.put(sameCharString, charLocList.get(j));
                    System.out.println("wordLocMap " + wordLocMap + wordLocMap.get(sameCharString).toString());
                }
            }
            // This new character is a palindrome itself
            wordLocMap.put(single, new Integer(i));
            // add a new location of this character
            charLocList.add(new Integer(i));
        }
        // The map contains all palindrome sequences
        System.out.println(wordLocMap.toString());
        System.out.println(charLocMap.toString());
        return wordLocMap.keySet();
    }

    // Method using binary search to find the biggest location that is smaller than loc
    private Integer findLoc(List<Integer> charLocList, Integer loc) {
        int low = 0;
        int hi = charLocList.size() - 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (loc <= charLocList.get(mid)) {
                hi = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (hi < 0) ? charLocList.get(0) : charLocList.get(hi);
    }


    // The map that will contain the location of each characters in the string
    private Map<Character, List<Integer>> _charIdxMap;
    // The map below will contains the list of palindromes of the string
    private Map<String, List<String>> _palindromeMap;

    private class Range {
        // both startIdx and endIdx are exclusive
        public int startIdx;
        public int endIdx;

        Range(int s, int e) {
            startIdx = s;
            endIdx = e;
        }
    }

    public Set<String> findPalindromesHard(String s) {
        Set<String> result = new HashSet<String>();
        result.addAll(findPalindromeList(s));
        return result;
    }

    public List<String> findPalindromeList(String s) {
        _charIdxMap = new TreeMap<Character, List<Integer>>();
        _palindromeMap = new HashMap<String, List<String>>();
// pre-processing the string to find out the location of each character
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            List<Integer> idxList = _charIdxMap.get(c);
            if (idxList == null) {
                idxList = new ArrayList<Integer>();
                _charIdxMap.put(c, idxList);
            }
            idxList.add(i);
        }
        return findPalindromeHelper(s, new Range(-1, len));
    }

    // the helper recursive function to find palindromes in string s within range
    private List<String> findPalindromeHelper(String s, Range range) {
        String s1 = s.substring(range.startIdx + 1, range.endIdx);
// check the string in the map to avoid processing the same string again for speedup
        List<String> result = _palindromeMap.get(s1);
        if (result != null) {
            return result;
        }
        result = new ArrayList<String>();
        _palindromeMap.put(s1, result);
// Iterate potential palindromic matches
        for (Map.Entry<Character, List<Integer>> entry : _charIdxMap.entrySet()) {
            List<Integer> charIdxList = entry.getValue();
            if (range.endIdx <= charIdxList.get(0)
                    || range.startIdx >= charIdxList.get(charIdxList.size() - 1)) {
// If no character is within this range, continue
                continue;
            }
            String single = entry.getKey().toString();
// find the smaller range that starts and ends with this character
            Range newRange = findInnerRange(charIdxList, range);
            if (newRange.endIdx >= newRange.startIdx) {
                result.add(single);
                if (newRange.endIdx > newRange.startIdx) {
                    result.add(single + single);
// find all the palindromes of the substring excluding the head and tail
                    List<String> innerResult = findPalindromeHelper(s, newRange);
                    for (String subs : innerResult) {
                        result.add(single + subs + single);
                    }
                }
            }
        }
        return result;
    }

    // find a smaller range than a given range in idxList
    private Range findInnerRange(List<Integer> idxList, Range range) {
// binary search to find the first idx that is bigger than startIdx
        int low = 0;
        int hi = idxList.size() - 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (idxList.get(mid) <= range.startIdx) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        Integer newStartIdx = idxList.get(low);
// binary search to find the first idx that is smaller than endIdx
        hi = idxList.size() - 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (idxList.get(mid) < range.endIdx) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return new Range(newStartIdx, idxList.get(hi));
    }
}