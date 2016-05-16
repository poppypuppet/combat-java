package LinkedIn;

/**
 * Followup questions:
 * If a Rankable's rank is defined as it's last access time, the retain-best cache becomes an LRU cache.
 * If the candidate doesn't know the 'standard' map + list LRU solution, ask them to re-implement the retain-best cache to take advantage of knowing what the Rank function is
 * If the candidate does know the standard LRU solution, ask them to contrast the performance of their retain-best cache to the standard LRU
 * (Hard mode) How would you need to enhance your solution to handle Rankables that frequently changed Rank?
 * LRU is the first step of this Objects increase in rank each time they are accessed.
 * Another variant might be Most/Least Frequently Used, where rank increases or decreases by one each access
 * If you had a cache of a graph that traversals were happening on,
 * you could declare a rank based on where the traversal was at the moment,
 * and so ranks could change without being accessed (rank would be a function of how close to the traversal front the node was).
 * In the worst case, this could trigger a full re-rank of the cache on each cache miss.
 * <p>
 * Interviewer tips: This is a variant of an LRU cache, but with (initially) (mostly) static Rank to somewhat simplify the
 * problem. The core of the question is that there are two indexes that are needed (one to support get() based on Key,
 * one to support fast eviction based on rank), and the candidate must keep them in sync during stores and evictions.
 * The static Rank also introduces the possibility that a newly fetched T will be the lowest ranked,
 * and good candidates will ask about behavior there.
 */
public class RetainBestCache<K, T extends Rankable> {
    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
    }

    /**
     * Gets some data. If possible, retrieves it from cache to be fast.
     * If the data is not cached, retrieves it from the data source.
     * If the cache is full, attempts to cache the returned * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
        return null;
    }

    /**Example Solutions
     As mentioned above, there are two data structures, one to serve get() and one to serve evict
     The Get structure needs to support get by key and remove by key. HashMaps are basically the only optionhere.
     The Evict structure needs to support getlowestrank and insertbyrank.
     In almost all cases, this will be a structure of Keys, sorted by the corresponding Rankable's rank.
     There are a few options:
     Heaps are generally good for the base problem, since we don't care about the not lowest ranks. They aren't as good for Most Frequently Used.
     Trees are also generally good, and perform well for small re-ranks like most/least frequently used.
     However, they do poorly when the entire tree must be reranked
     Lists work well for LRU, obviously.
     Sorted Arrays can work, since they never get resized.
     */
}