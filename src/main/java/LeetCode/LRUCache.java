package LeetCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUCache {
    HashMap<Integer, Integer> indexes;
    LinkedList<Integer> orders; // 用linkedlist会超时,online用arraylist.
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.indexes = new HashMap<>(capacity);
        this.orders = new LinkedList<>();
    }

    public int get(int key) {
        Integer value = indexes.get(key);
        if (value == null) {
            return -1;
        } else {
            remove(key, orders);
            orders.add(key);
            return value;
        }
    }

    public void set(int key, int value) {
        Integer old = indexes.get(key);
        if (old == null) {
            if (orders.size() < capacity) {
                indexes.put(key, value);
                orders.add(key);
            } else {
                int leastKey = orders.pop();
                indexes.remove(leastKey);
                indexes.put(key, value);
                orders.offer(key);
            }
        } else {
            indexes.put(key, value);
            remove(key, orders);
            orders.add(key);
        }
    }

    private void remove(int key, LinkedList<Integer> orders) {
        Iterator<Integer> it = orders.iterator();
        while (it.hasNext()) {
            int val = it.next();
            if (val == key) {
                it.remove();
            }
        }
    }
}
