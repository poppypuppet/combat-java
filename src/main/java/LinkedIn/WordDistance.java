package LinkedIn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Leetcode Shortest Word Distance I II
 * 我们其实需要遍历一次数组就可以了，我们用两个变量p1,p2初始化为-1，
 * 然后我们遍历数组，遇到单词1，就将其位置存在p1里，若遇到单词2，就将其位置存在p2里，如果此时p1, p2都不为-1了，那么我们更新结果，
 * <p>
 * 这道题是之前那道Shortest Word Distance的拓展，不同的是这次我们需要多次调用求最短单词距离的函数，那么用之前那道题的解法二和三就非常不高效，
 * 而当时我们摒弃的解法一的思路却可以用到这里，我们用哈希表来建立每个单词和其所有出现的位置的映射，
 * 然后在找最短单词距离时，我们只需要取出该单词在哈希表中映射的位置数组进行两两比较即可.
 * <p>
 * 我们可以优化上述的代码，使查询的复杂度由上面的O(MN)变为O(M+N)，其中M和N为两个单词的长度，
 * 我们需要两个指针i和j来指向位置数组中的某个位置，开始初始化都为0，
 * 然后比较位置数组中的数字，将较小的一个的指针向后移动一位，直至其中一个数组遍历完成即可，
 * <p>
 * This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text.
 * <p>
 * "quick" appears twice in the input. There are two possible distance values for "quick" and * (3 ‐ 1) = 2 and (4 ‐ 3) = 1.
 * Since we have to return the shortest distance between the two words we return 1.
 */

public class WordDistance {
    private List<String> words;

    public static void main(String argv[]) {
        WordDistance finder = new WordDistance(Arrays.asList("the", "quick", "brown", "fox", "quick"));
        assert (finder.distance("fox", "the") == 3);
        assert (finder.distance("quick", "fox") == 1);
    }

    public WordDistance(List<String> words) {
        this.words = words;
    }

    public int distance(String wordOne, String wordTwo) {
        int minDist = Integer.MAX_VALUE;
        int startIndex = 0;
        String currentWord = null;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (word.equalsIgnoreCase(wordOne) || word.equalsIgnoreCase(wordTwo)) {
                if (currentWord == null) {
                    currentWord = word;
                }
                if (currentWord.equalsIgnoreCase(word)) {
// Update start position
                    startIndex = i;
                }
                else {
// Other word: pair found. Compute distance and reset
                    int distance = i - startIndex;
                    if (distance < minDist) {
                        minDist = distance;
                    }
                    currentWord = word;
                    startIndex = i;
                }
            }
        }
        return minDist; // returns MAX_INT if either word is not present in the corpus.
    }


    // INCREASE
    private Map<String, List<Integer>> index;

    public WordDistance(List<String> words, boolean hard) {
        index = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (!index.containsKey(word)) {
                index.put(word, new LinkedList<Integer>());
            }
            index.get(word).add(i); // Helpfully adds to the end of the list, so they are in }
        }
    }

    public int distanceHARD(String wordOne, String wordTwo) {
        List<Integer> lOne = index.get(wordOne);
        List<Integer> lTwo = index.get(wordTwo);
        if (lOne == null || lTwo == null) {
            return Integer.MAX_VALUE;
        } // consistent behavior
        int idxOne = 0;
        int idxTwo = 0;
        int minDist = Integer.MAX_VALUE;
        while (idxOne < lOne.size() && idxTwo < lTwo.size()) {
            int indexOne = lOne.get(idxOne);
            int indexTwo = lTwo.get(idxTwo);
            // calculate distance
            minDist = Math.min(minDist, Math.abs(indexOne - indexTwo));
            if (indexOne < indexTwo) {
                idxOne += 1;
            }
            else {
                idxTwo += 1;
            } // increment the lower
        }
        // At this point, one of the lists has run out. Note that the last element of the list
        // less than the current element in the "other" list (since we increment the lower one).
        // Any comparison of elements to the right of the current
        // element in the "other" list will result in greater distances, so more work is pointless,
        return minDist;
    }
}
