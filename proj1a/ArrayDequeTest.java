import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/** Tests ArrayDeque class */
public class ArrayDequeTest {

    /** Tests if ArrayDeque works when it's empty */
    @Test
    public void testEmpty() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertEquals(null, q.get(0));
        assertEquals(null, q.removeFirst());
        assertEquals(null, q.removeLast());
    }

    /**
     * Tests if ArrayDeque.addFirst & ArrayDeque.addLast methods without resizing.
     * */
    @Test
    public void testAdd() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addFirst(0);
        q.addLast(1);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());

        int actual0th = q.get(0);
        int actual1st = q.get(1);
        assertEquals(0, actual0th);
        assertEquals(1, actual1st);
    }

    /**
     * Tests if ArrayDeque.removeFirst & ArrayDeque.removeLast method without resizing.
     * */
    @Test
    public void testRemove() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addFirst(0);
        q.addLast(1);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());

        int actualFirst = q.removeFirst();
        int actualLast = q.removeLast();
        assertEquals(0, actualFirst);
        assertEquals(1, actualLast);
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        q.addFirst(1);
        q.addFirst(0);
        int actualLast1 = q.removeLast();
        int actualLast0 = q.removeLast();
        assertEquals(1, actualLast1);
        assertEquals(0, actualLast0);
    }

    /**
     * Tests if ArrayDeque.addFirst & ArrayDeque.addLast methods with resizing.
     * */
    @Test
    public void testAddResize() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            q.addFirst(i);
        }
        assertEquals(10, q.size());
        int actualFirst = q.get(0);
        int actualLast = q.get(9);
        assertEquals(9, actualFirst);
        assertEquals(0, actualLast);

        for (int i = 10; i < 100; i++) {
            q.addLast(i);
        }
        actualLast = q.get(99);
        assertEquals(100, q.size());
        assertEquals(99, actualLast);
    }

    /**
     * Tests if ArrayDeque.removeFirst & ArrayDeque.removeLast method with resizing.
     * @NOTE: To check memory usage, ArrayDeque.capacity should be made public and the 4 lines below
     *  should be commented in.
     * */
    @Test
    public void testRemoveResize() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i <= 100; i++) {
            q.addFirst(i);
        }
        for (int i = 100; i >= 10; i--) {
            int removed = q.removeFirst();
//            double ratio = (double) q.size() / q.capacity;
            assertEquals(i, removed);
//            assertTrue(ratio >= 0.25);
        }

        for (int i = 10; i <= 1000; i++) {
            q.addLast(i);
        }
        for (int i = 1000; i >= 10; i--) {
            int removed = q.removeLast();
//            double ratio = (double) q.size() / q.capacity;
            assertEquals(i, removed);
//            assertTrue(ratio >= 0.25);
        }
    }

    /** Random test */
    @Test
    public void testRandom() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        Random r = new Random();
        int firstAdd = 1000;
        int size1 = 0 + firstAdd;
        int firstRemove = 900;
        int size2 = size1 - firstRemove;
        int secondAdd = 10000;
        int size3 = size2 + secondAdd;
        int secondRemove = 9000;
        int size4 = size3 - secondRemove;

        /** First test: add & remove */
        for (int i = 0; i < firstAdd; i++) {
            if (r.nextInt(2) < 1) {
                q.addFirst(i);
            } else {
                q.addLast(i);
            }
        }
        assertEquals(size1, q.size());
        for (int i = 0; i < firstRemove; i++) {
            if (r.nextInt(2) < 1) {
                q.removeFirst();
            } else {
                q.removeLast();
            }
        }
        assertEquals(size2, q.size());

        /** Second test: add & remove & get */
        int first = 0;
        int last = 0;
        int removed = 0;
        for (int i = 0; i < secondAdd; i++) {
            if (r.nextInt(2) < 1) {
                q.addFirst(i);
            } else {
                q.addLast(i);
            }
        }
        assertEquals(size3, q.size());
        for (int i = 0; i < secondRemove; i++) {
            if (r.nextInt(2) < 1) {
                first = q.get(0);
                removed = q.removeFirst();
                assertEquals(first, removed);
            } else {
                last = q.get(size3 - i - 1);
                removed = q.removeLast();
                assertEquals(last, removed);
            }
        }
        assertEquals(size4, q.size());
    }

    /** Fill up & empty */
    @Test
    public void testFill() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            q.addFirst(i);
        }
        for (int i = 0; i < 8; i++) {
            int removed = q.removeFirst();
            assertEquals(7 - i, removed);
        }
        assertEquals(0, q.size());
        for (int i = 0; i < 16; i++) {
            q.addFirst(i);
        }
        assertEquals(16, q.size());
    }

    /** Multi-instantiating test */
    @Test
    public void testMulti() {
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();

        for (int i = 0; i < 8; i++) {
            q1.addFirst(i);
            q2.addLast(i);
        }
        for (int i = 0; i < 8; i++) {
            int removed1 = q1.removeFirst();
            int removed2 = q2.removeLast();
            assertEquals(7 - i, removed1);
            assertEquals(7 - i, removed2);
        }
        assertEquals(0, q1.size());
        assertEquals(0, q2.size());
        for (int i = 0; i < 16; i++) {
            q1.addFirst(i);
            q2.addLast(i);
        }
        assertEquals(16, q1.size());
        assertEquals(16, q2.size());
    }

}
