package LinkedIn;

import java.util.List;

/**
 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
 * For example:
 * Given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one *2 at depth 1)
 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and *one 6 at depth 3)
 */
class NestedListWeightSum {
    public int depthSum(List<NestedInteger> input) {
        return depthSumDFS(input, 1);
    }

    private int depthSumDFS(List<NestedInteger> input, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : input) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            }
            else {
                sum += depthSumDFS(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }
}