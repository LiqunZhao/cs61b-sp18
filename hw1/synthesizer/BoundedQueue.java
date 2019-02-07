package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /**
     * Returns size of the buffer
     */
    int capacity();

    /**
     * Returns number of items currently in the buffer
     */
    int fillCount();

    /**
     * Adds item x to the end of queue
     */
    void enqueue(T x);

    /**
     * Deletes and returns item from the front of queue
     */
    T dequeue();

    /**
     * Returns (but does not delete) item from the front of queue
     */
    T peek();

    @Override
    Iterator<T> iterator();

    /**
     * Returns if the buffer is empty
     */
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns if the buffer is full
     */
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }

}
