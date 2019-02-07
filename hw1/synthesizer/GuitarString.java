package synthesizer;


public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private BoundedQueue<Double> buffer; // buffer to store sound data

    /**
     * Creates a guitar string of the given frequency.
     */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(0.0);
        }
    }


    /**
     * Plucks the guitar string by replacing the buffer with white noise.
     */
    public void pluck() {
        for (double i : buffer) {
            double r = Math.random() - 0.5;
            buffer.dequeue();
            buffer.enqueue(r);
        }
    }

    /**
     * Advances the simulation one time step by performing one iteration of the
     * Karplus-Strong algorithm.
     */
    public void tic() {
        double front = buffer.dequeue();
        double next = buffer.peek();
        double plucked = DECAY * (front + next) / 2;
        buffer.enqueue(plucked);
    }

    /* Returns the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }

}
