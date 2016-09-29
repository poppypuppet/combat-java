package LeetCode;

import java.util.HashMap;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreak(s, wordDict, 0, new HashMap<Integer, Boolean>());
    }

    public boolean wordBreak(String s, Set<String> wordDict, int start, HashMap<Integer, Boolean> cache) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i < s.length() + 1; i++) {
            String sub = s.substring(start, i);
            if (wordDict.contains(sub)) {
                boolean b;
                if (cache.containsKey(i)) {
                    b = cache.get(i);
                } else {
                    b = wordBreak(s, wordDict, i, cache);
                    cache.put(i, b);
                }
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }
}
