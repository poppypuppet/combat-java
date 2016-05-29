package LinkedIn;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

public class LRUCache {
    private final int capacity;
    private ListNode begin;
    private ListNode end;
    private Map<Integer, ListNode> index;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        index = new HashMap<>();
        begin = new ListNode(-1, -1, null, end);
        end = new ListNode(-1, -1, begin, null);
    }

    public int get(int key) {
        if (index.containsKey(key)) {
            ListNode n = index.get(key);
            remove(n);
            add(n);
            return n.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (index.containsKey(key)) {
            ListNode n = index.get(key);
            n.value = value;
            remove(n);
            add(n);
        } else {
            if (index.size() >= capacity) {
                index.remove(begin.next.key);
                remove(begin.next);
            }
            ListNode n = new ListNode(key, value);
            add(n);
            index.put(key, n);
        }
    }

    private void remove(ListNode n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void add(ListNode n) {
        end.prev.next = n;
        n.prev = end.prev;
        n.next = end;
        end.prev = n;
    }
}