package synthesizer;


public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int capacity;
    protected int fillCount;

    @Override
    /** Returns if the buffer is empty */
    public int capacity() {
        return capacity;
    }

    @Override
    /** Returns if the buffer is full */
    public int fillCount() {
        return fillCount;
    }

}
