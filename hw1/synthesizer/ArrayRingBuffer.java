package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    private int first;          // index for the next dequeue or peek
    private int last;           // index for the next enqueue
    private T[] rb;             // array for storing the buffer data

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        /** this` should be specified because the local variable `capacity` here shadows the field
         *  inherited from AbstractBoundedQueue. */
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Helper method to increase first or last
     */
    private int onePlus(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Adds x to the end of the ring buffer.
     * If there is no room, then throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = onePlus(last);
        fillCount += 1;
    }

    /**
     * Dequeue the oldest item in the ring buffer.
     * If the buffer is empty, then throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T item = rb[first];
        rb[first] = null;
        first = onePlus(first);
        fillCount -= 1;
        return item;
    }

    /**
     * Returns the oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T item = rb[first];
        return item;
    }

    private class QueueIterator implements Iterator<T> {

        int numRemain;
        int current;

        QueueIterator() {
            numRemain = fillCount();
            current = first;
        }

        @Override
        public boolean hasNext() {
            return numRemain > 0;
        }

        @Override
        public T next() {
            T item = rb[current];
            numRemain -= 1;
            current = onePlus(current);
            return item;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }


}
