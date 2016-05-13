package LinkedIn;

import java.util.List;

/*
* threadSafe bounded blocking queue implementation.
* Expected to be used in a Producer‐>Consumer pattern
*/
public interface MultiPutBlockingBoundedQueue<T> {
    /*
    * Sets the capacity of the buffer. Can be called only once.
    * If called more than once or if the passed capacity is <= 0,
    * throw an exception.
    */
    public void init(int capacity) throws Exception;

    /*
    * Get the next item from the queue
    * throws Exception if not initialized
    */
    public Object get() throws Exception;

    /*
    * Put the item to the tail of the queue.
    * throws Exception if not initialized
    */
    public void put(T obj) throws Exception;

    /*
    * Put the list of items in an atomic manner.
    * The list can be more than the capacity
    * throws Exception if not initialized
    */
    public void multiput(List<T> objs) throws Exception;
}
