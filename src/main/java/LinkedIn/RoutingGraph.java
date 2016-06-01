package LinkedIn;

import java.util.Arrays;

/**
 * This question is a mixture of simple algorithms + distribution.
 * I think it's a pretty good opener since it's not that hard. If
 * the candidate is really good, this should only take 20-25
 * minutes and leave room for you to ask something truly hard.
 * <p>
 * Given an adjacency list representation of a graph where edges are sorted by id,
 * how can you calculate network distances of 0th 3rd degree?
 * Give the candidates the Graph interface, and ask them to implement test0 - test3.
 * <p>
 * The key insight the candidate needs to make is that the graph is symmetric, so you can speed a lot of tests up.
 * Many candidates will attempt to do some variant of BFS or A*. Stop them quickly and ask for a better solution.
 */
public class RoutingGraph {
    // Returns a sorted list of memberId's connections
    public int[] getConnections(int memberId) {
        return new int[0];
    }

    boolean test0(int srcId, int destId) {
        return srcId == destId;
    }

    boolean test1(int srcId, int destId) {
        return Arrays.binarySearch(getConnections(srcId), destId) >= 0;
    }

    // Checks to see if there are any elements in common between the two arrays
    boolean testIntersection(int[] array1, int[] array2) {
        int i = 0, j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] == array2[j]) return true;
            else if (array1[i] < array2[j]) i++;
            else j++;
        }
        return false;
    }

    boolean test2(int srcId, int destId) {
        return testIntersection(getConnections(srcId), getConnections(destId));
    }

    boolean test3(int srcId, int destId) {
        int[] srcConns = getConnections(srcId);
        int[] destConns = getConnections(destId);
        for (int i = 0; i < srcConns.length; i++)
            if (testIntersection(getConnections(srcConns[i]), destConns)) return true;
        return false;
    }

    int getDistance(int srcId, int destId) {
        if (test0(srcId, destId)) return 0;
        if (test1(srcId, destId)) return 1;
        if (test2(srcId, destId)) return 2;
        if (test3(srcId, destId)) return 3;
        return -1;
    }
}
