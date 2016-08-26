package LinkedIn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Given a continuous stream of messages, we would like to implement a sliding window on which we can do some
 * basic computation. I usually give an example where you have the stream consisting of stock trades and you want to
 * find the average price of the stock in the past 5 minutes, at any point in time.
 * This question can be asked as an algorithms and data structures question to junior candidates, where I give them a
 * pass on concurrency. For Sr/Pr level, the focus should be on concurrency and getting the maximum scalability out of
 * the system.
 * <p>
 * Define the following constraints, simplifications.
 * 1. Messages are coming in at a very high rate. It's important that the data structure keep up i.e. addMsg needs to be
 * very efficient.
 * 2. getAvg, getMsg calls are also called frequently in multiple threads. So it's important that they be fast. Try O(1).
 * Iterating over all the messages to retrieve a message or compute average is for example unacceptable.
 * 3. getMsg, getAvg must be correct. Expired messages should not be considered.
 * 4. Assume a reasonable library to be available. Implementing the underlying data structures is not the goal of this
 * problem.
 * 5. getCurrentTime() is available that gives time in microsecond granularity.
 * 6. You have sufficient memory to hold the window (with some additional buffer) but it's not okay to simply keep adding
 * messages and not cleaning up expired messages.
 * <p>
 * Step1: Identify the basic data structures and algorithms (10-15 mins)
 * The key based access requires a lookup structure such as hashmap. The time based purging requires a data
 * structure such as circular buffer. Answering getAvg quickly requires that a running sum and count be maintained.
 * Message timestamp needs to be maintained so that on a get, stale message can be identified easily. Since getAvg
 * needs to be correct, getAvg needs to adjust the running stats. A background thread can be used for cleanup in
 * addition.
 * <p>
 * Step 2: Implement concurrent methods and identify the right locking granularity (20-25 mins)
 * It should be clarified that adding synchronized to the methods and effectively serializing access is not acceptable.
 * Simply using read write locks is not very useful since addMsg is the most frequent call. addMsg has to take a write
 * lock. getMsg can take a read lock. Concurrency of getMsg can be improved by locking hashtable buckets instead of
 * the entire hashtable. getAvg requires inspecting older messages and adjusting the running stats to account for
 * expired messages. Inspecting messages and computing the amount to be adjusted does not require a write lock, only
 * adjusting the running stats. But concurrent getAvg calls can cause race conditions unless locking is done carefully.
 * Locking access to the data structures during addMsg and getAvg also conflict.
 * <p>
 * Step 3: Partitioning data structures (10-15 mins)
 * Locking data structures to remove expired message and add new messages limit some concurrency. Since messages
 * are removed in the order of their arrival, partitioning the time window helps in doing these operations without running
 * into each other.
 */
public class Window {
    // Messages are <key, val> pairs

    private final int volumn;
    private final Object lock;
    private ConcurrentHashMap<Long, Long> values;
    private ConcurrentLinkedQueue<Long> orders;
    // Define a window of a certain size in microsecond granularity
    public Window(int nMicrosecs) {
        values = new ConcurrentHashMap<>(nMicrosecs);
        volumn = nMicrosecs;
        lock = new Object();
    }

    // add a new incoming message
    public void addMsg(Msg m) {
        boolean isRemove = false;
        synchronized (lock) {
            orders.offer(m.m_key);
            if (orders.size() > volumn) {
                isRemove = true;
            }
        }

        Long value = values.putIfAbsent(m.m_key, m.m_val);
        if (value == null) {
            value = m.m_val;
        }
        if (isRemove) {
            Long key = orders.poll();
            values.remove(key);
        }
    }

    // get a message given a key. If the message does not exist or message is older than t
    public Msg getMsg(long key) {
        Long value = values.get(key);
        if (value == null) {
            return null;
        } else {
            return new Msg(key, value);
        }
    }

    // get the average of message values in the window. Like getMsg, it's important to be correct.
    public Double getAvg() {
        AtomicLong sum = new AtomicLong();
        values.forEach((key, value) -> {
            while (!sum.compareAndSet(sum.get(), sum.get() + value)) ;
        });
        return sum.doubleValue() / volumn;
    }

    /**
     * 在使用锁来协调多线程间并发访问的模式下,减小对锁的竞争可以有效提高并发性。有两种方式可以减小对锁的竞争：
     * 1.减小请求 同一个锁的 频率。
     * 2.减少持有锁的 时间。
     * ConcurrentHashMap 的高并发性主要来自于三个方面：
     * 1.用分离锁实现多个线程间的更深层次的共享访问。
     * 2.用 HashEntery 对象的不变性来降低执行读操作的线程在遍历链表期间对加锁的需求。
     * 3.通过对同一个 volatile 变量的写/读访问,协调不同线程间读/写操作的内存可见性。
     * volatile的读和写建立了一个happens-before关系，类似于申请和释放一个互斥锁
     * <p>
     * 1.1 使用分离锁,减小了请求 同一个锁的频率。
     * 2.2 通过 HashEntery 对象的不变性及对同一个 Volatile 变量的读/写来协调内存可见性,使得读操作大多数时候不需要加锁就能成功获取到需要的值。
     * 由于散列映射表在实际应用中大多数操作都是成功的读操作,所以 2 和 3 既可以减少请求同一个锁的频率,也可以有效减少持有锁的时间。
     * 3.3 通过减小请求同一个锁的频率和尽量减少持有锁的时间,使得 ConcurrentHashMap 的并发性相对于 HashTable 和用同步包装器包装的 HashMap有了质的提高。
     */
    public static class Msg {
        long m_key; // unique
        long m_val;

        public Msg() {
        }

        public Msg(long key, long value) {
            m_key = key;
            m_val = value;
        }
    }
}

