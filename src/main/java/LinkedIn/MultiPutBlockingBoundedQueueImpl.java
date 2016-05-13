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
 * <p>
 * <p>
 * improvement:
 * Condition 将 Object 监视器方法(wait,notify 和 notifyAll)分解成截然不同的对象,以便通过将这些对象与任意 Lock 实现组合使用.
 * 为每个对象提供多个等待 set (wait-set).其中,Lock 替代了 synchronized 方法和语句的使用,Condition 替代了 Object 监视器方法的使用.
 * 这是一个处于多线程工作环境下的缓存区,缓存区提供了两个方法,put和take,put是存数据,take是取数据,内部有个缓存队列.
 * 有多个线程往里面存数据和从里面取数据,其缓存队列(先进先出后进后出)能缓存的最大数值是100,多个线程间是互斥的.
 * 当缓存队列中存储的值达到100时,将写线程阻塞,并唤醒读线程,当缓存队列中存储的值为0时,将读线程阻塞,并唤醒写线程,下面分析一下代码的执行过程：
 * 1. 一个写线程执行,调用put方法;
 * 2. 判断count是否为100,显然没有100;
 * 3. 继续执行,存入值;
 * 4. 判断当前写入的索引位置++后,是否和100相等,相等将写入索引值变为0,并将count+1;
 * 5. 仅唤醒读线程阻塞队列中的一个;
 * 6. 一个读线程执行,调用take方法;
 * 7. ...
 * 8. 仅唤醒写线程阻塞队列中的一个;
 * 这就是多个Condition的强大之处,假设缓存队列中已经存满,那么阻塞的肯定是写线程,唤醒的肯定是读线程,相反,阻塞的肯定是读线程,唤醒的肯定是写线程.
 * 那么假设只有一个Condition会有什么效果呢,缓存队列中已经存满,这个Lock不知道唤醒的是读线程还是写线程了,
 * 如果唤醒的是读线程,皆大欢喜,如果唤醒的是写线程,那么线程刚被唤醒,又被阻塞了,这时又去唤醒,这样就浪费了很多时间.
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