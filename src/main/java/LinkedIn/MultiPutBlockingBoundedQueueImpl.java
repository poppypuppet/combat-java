package LinkedIn;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * Evaluation
 * A great candidate :
 * Will understand the writers (in the presence of multi-put)
 * need to be synchronized in addition to global locking.
 * Will implement correctly with thread-safety.
 * Will not require any assistance in getting the code right.
 * Implement in 20-30 mins.
 * A good candidate:
 * Will need some hints before he understands writers (in the presence of multi-put)
 * need to be synchronized in addition
 * to global locking.
 * Will implement correctly with thread-safety.
 * Will not require any assistance in getting the code right. Might need help with the Java (or any other language)
 * specific synchronization constructs.
 * Implement in 3040
 * mins.
 * A poor candidate:
 * Implements an incorrect solution w.r.t thread-safety
 * or correctness.
 * His/her concurrency fundamentals not strong enough to reason about the state of a multithreaded
 * system using the
 */
public class MultiPutBlockingBoundedQueueImpl<T> implements MultiPutBlockingBoundedQueue<T> {
    private boolean _isInitDone = false;
    private int _capacity = 0;
    private LinkedList _buffer = null;
    private int _currSize = 0;
    private Lock _lock;
    private Lock _writerLock;
    private Condition _notEmpty;
    private Condition _notFull;

    private Object lock = new Object();

    private ConcurrentLinkedQueue<T> concurrentLinkedQueue = null;

    private LinkedList<T> queue = null;
    private int capacity;

    public void init(int capacity) throws Exception {
        if (queue != null || capacity < 0) {
            throw new Exception("Shit!");
        }
        else {
            this.queue = new LinkedList<T>();
            this.capacity = capacity;
        }
    }

    public T get() throws Exception {
        synchronized (lock) {
            if (queue == null) {
                throw new Exception("Shit!");
            }
            else {
                return queue.getLast();
            }
        }
    }


    public void put(T obj) throws Exception {
        synchronized (lock) {
            if (queue == null || queue.size() > capacity) {
                throw new Exception("Shit!");
            }
            else {
                queue.add(obj);
            }
        }
    }

    public void multiput(List<T> objs) throws Exception {
        synchronized (lock) {
            if (queue == null) {
                throw new Exception("Shit!");
            }
            else {
                queue.addAll(objs);
                capacity = queue.size();
            }
        }
    }
}