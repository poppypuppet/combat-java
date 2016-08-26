package LinkedIn;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
 * For example:
 * Given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one *2 at depth 1)
 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and *one 6 at depth 3)
 */
class NestedListWeightSum {
    // global variables to avoid complicated recursion return val
    int unweightedSum;
    int maxLevel;

    public int depthSum(@NotNull List<NestedInteger> input) {
        return depthSumDFS(input, 1);
    }

    private int depthSumDFS(@NotNull List<NestedInteger> input, int depth) {
        int sum = 0;
        for (NestedInteger i : input) {
            if (i.isInteger()) {
                sum += i.getInteger() * depth;
            } else {
                sum += depthSumDFS(i.getList(), depth + 1);
            }
        }
        return sum;
    }

    /**
     * Follow up question: how to solve this problem with O(1) storage requirement.
     * Interviewer tips: The original Depth Sum will give you a good starting point. Candidate may try to write a method to
     * first calculate the max depth. Stop him/her and ask is there better approach. Average candidate may use a List or
     * Map to store the sum of each level, and iterate the collection at the end of the code to sum up. This is OK to junior
     * candidates, but try to push harder for experienced candidates. Ask them the follow up question. If they pursue on
     * solving the follow up with some "magic" O(1) data structure, direct them to the right direction by hinting to consider
     * mathematical approach. If still stuck, show the equation like
     * 3x + 2y + z = 4(x + y + z) - (x + 2y + 3z)
     * 遍历时候取得每层和,外部在进行想加.
     */
    public int reverseDepthSum(@NotNull List<NestedInteger> input) {
        ArrayList<Integer> levels = new ArrayList<>();
        reverseDepthSumDFS(input, 0, levels);
        int weight = levels.size();
        int sum = 0;
        for (Integer i : levels) {
            sum += weight * i;
            weight--;
        }
        return sum;
    }

    private void reverseDepthSumDFS(@NotNull List<NestedInteger> input, int index, ArrayList<Integer> levels) {
        if (levels.size() <= index) {
            levels.add(0);
        }
        int sum = 0;
        for (NestedInteger item : input) {
            if (item.isInteger()) {
                sum += item.getInteger();
            } else {
                reverseDepthSumDFS(item.getList(), index + 1, levels);
            }
        }
        levels.set(index, levels.get(index) + sum);
    }

    /**
     * Two and One traversal use O(n) space
     */
    public int reverseDepthSumTwoTraversals(List<NestedInteger> input) {
        return (input == null) ? 0 : helperTwoTraversal(input, depth(input) - 1);
    }

    private int helperTwoTraversal(List<NestedInteger> input, int weight) {
        int sum = 0;
        for (NestedInteger item : input) {
            if (item == null) {
                // ignore it
            } else if (item.isInteger()) {
                sum += item.getInteger() * weight;
            } else {
                sum += helperTwoTraversal(item.getList(), weight - 1);
            }
        }
        return sum;
    }

    public int reverseDepthSumOneTraversal(List<NestedInteger> input) {
        if (input == null || input.isEmpty()) {
            return 0;
        } else {
            unweightedSum = 0;
            maxLevel = 1;
            int weightedSum = helperOneTraversal(input, 1);
            return (maxLevel + 1) * unweightedSum - weightedSum;
        }
    }

    private int helperOneTraversal(List<NestedInteger> input, int depth) {
        int weightedSum = 0;
        for (NestedInteger item : input) {
            if (item == null) {
// ignore it
            } else if (item.isInteger()) {
                weightedSum += item.getInteger() * depth;
                unweightedSum += item.getInteger();
            } else {
                weightedSum += helperOneTraversal(item.getList(), depth + 1);
                maxLevel = Math.max(maxLevel, depth + 1);
            }
        }
        return weightedSum;
    }

    private int depth(List<NestedInteger> input) {
        int maxChild = 0;
        for (NestedInteger child : input) {
            if (child != null) {
                maxChild = Math.max(maxChild, child.isInteger() ? 1 : depth(child.getList()));
            }
        }
        return maxChild + 1;
    }
}