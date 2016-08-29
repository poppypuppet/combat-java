package LeetCode;

import java.util.Iterator;

/**
 * 缓存法
 * 复杂度
 * 时间 O(1) 空间 O(1)
 * 思路
 * 为了能peek后下次next还得到同样的数字，我们要用一个缓存保存下一个数字。这样当peek时候，返回缓存就行了，迭代器位置也不会变。
 * 当next的时候除了要返回缓存，还要将缓存更新为下一个数字，如果没有下一个就将缓存更新为null。
 */
class PeekingIterator<T> implements Iterator<T> {
    T cache;
    Iterator<T> it;

    public PeekingIterator(Iterator<T> iterator) {
        this.cache = iterator.next();
        this.it = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        T res = cache;
        cache = it.hasNext() ? it.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return cache != null;
    }
}
