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
public abstract class RoutingGraph {
    // Returns a sorted list of memberId's connections
    public abstract int[] getConnections(int memberId);

    // O(1)
    boolean test0(int srcId, int destId) {
        return srcId == destId;
    }

    // O(logn)
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

    // O(n)
    boolean test2(int srcId, int destId) {
        return testIntersection(getConnections(srcId), getConnections(destId));
    }

    // O(n*n)
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

    /**
     * Follow up:
     * What algorithmic improvements can you get if you have an extra method
     * that can return you a 2nd degree in constant time?
     * Explain that a second degree is a flattened,
     * sorted array of all the people you could reach through one of your connections.
     * <p>
     * Checking for 2nd degree becomes a binary search over the 2nd degree, or O( log(N^2) ) = O(log N)
     * Checking for 3rd degree becomes N binary searches, for O( N log(N^2) ) = O( N log(N) )
     */
    abstract int[] getSecondDegree(int memberId);

    /**
     * Follow up:
     * How would you distributed this graph? How do the algorithms change?
     * <p>
     * There are two solutions here.
     * 1. Horizontally shard the graph into partitions. Each partition holds all the connections for a portion of members.
     * 2. Vertically shard the graph into partitions. Each partition holds some for all members, but only keeps a portion of that member's connections. This is similar to search partitioning.
     * <p>
     * Horizontally shared graph
     * 1. 0th degree: Same algorithm run on the client.
     * 2. 1st degree: Send the dest id to the server and have it run a binary search for you, returning a boolean.
     * 3. 2nd degree: Get source & dest's connections from server in parallel, then run the merge algorithm. Ask
     * them about the case where the bandwidth between client and server is much lower than from server to
     * server. They should recognize that the best algorithm in that case is to have the server for the source's
     * connections fetch the dest's connections and perform the merge there, returning only a boolean to you
     * rather than two potentially large int[].
     * 4. 3rd degree: This one is very different when running in a horizontally distributed environment. The
     * obvious solution is to fetch the source's first degree, then for each connection run a 2nd degree test.
     * This uses N^2 network bandwidth and is very expensive. We can do much better because the graph is symmetric.
     * Start algorithm on the server for the dest's first degree.
     * Broadcast dest's first degree to each server (K parallel remote calls)
     * Group source's 1st degree by which server their data is on
     * For each grouping, have that server calculate a "partial 2nd degree" using that slice of the
     * source's first degree and intersect with dest's 1st degree. (K parallel remote calls)
     * <p>
     * Vertically shared graph
     * 1. 0th degree: Same algorithm just run on the client
     * 2. 1st degree: Send the dest id to each server and have it run a binary search on each server. If any of the
     * results are true, the test is true.
     * 3. 2nd degree: Fetch the connections for source and dest and run a merge intersection. To fetch the
     * connections, you'll have to merge the pieces together. This is easy if it's sharded by range since you can
     * just append them together.
     * 4. 3rd degree: The partitioning gets better for this case.
     * You can build "partial" second degrees for the source on each server without talking to other machines.
     * For each partial second degree, you can intersect that with the partial first degree on that server for the dest.
     * This requires only K remote calls with just the src and dest as parameters where K is the number of partitions.
     */
    public abstract void distribute();
}
