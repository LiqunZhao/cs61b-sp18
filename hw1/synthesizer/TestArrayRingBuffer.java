package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void isEmptyTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        assertTrue(arb.isEmpty());

        arb.enqueue(0);
        assertFalse(arb.isEmpty());
    }

    @Test
    public void isFullTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        assertFalse(arb.isFull());

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
    }

    @Test
    public void dequeueTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        Integer expected1 = 0;
        assertEquals(expected1, arb.dequeue());

        arb.enqueue(10);
        Integer expected2 = 1;
        assertEquals(expected2, arb.dequeue());
    }

    @Test
    public void peekTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        Integer expected1 = 0;
        assertEquals(expected1, arb.peek());
        assertTrue(arb.isFull());
    }

    @Test
    public void iteratorTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }

        int expected = 0;
        for (int i : arb) {
            assertEquals(expected, i);
            expected += 1;
        }
        assertEquals(10, expected);
    }


    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }

} 
