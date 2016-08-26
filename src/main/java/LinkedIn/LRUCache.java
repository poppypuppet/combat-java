package LinkedIn;

import Interface.DoubleListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * <p>
 * get(key) - Get the val (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, val) - Set or insert the val if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */

public class LRUCache {
    private final int capacity;
    private DoubleListNode begin;
    private DoubleListNode end;
    private Map<Integer, DoubleListNode> index;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        index = new HashMap<>();
        begin = new DoubleListNode(-1, -1, null, end);
        end = new DoubleListNode(-1, -1, begin, null);
    }

    public int get(int key) {
        if (index.containsKey(key)) {
            DoubleListNode n = index.get(key);
            remove(n);
            add(n);
            return n.value;
        } else {
            return -1;
        }
    }

    private void remove(DoubleListNode n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void add(DoubleListNode n) {
        end.prev.next = n;
        n.prev = end.prev;
        n.next = end;
        end.prev = n;
    }

    public void set(int key, int value) {
        if (index.containsKey(key)) {
            DoubleListNode n = index.get(key);
            n.value = value;
            remove(n);
            add(n);
        } else {
            if (index.size() >= capacity) {
                index.remove(begin.next.key);
                remove(begin.next);
            }
            DoubleListNode n = new DoubleListNode(key, value);
            add(n);
            index.put(key, n);
        }
    }
}