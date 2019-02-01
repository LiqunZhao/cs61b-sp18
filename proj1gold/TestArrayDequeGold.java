import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {

    private static final int nCall = 1000; // How many to call methods randomly
    private static String message = ""; // Store failure sequence

    /** Given uniformly distributed random double between 0 and 1,
     * randomly adds Integer to deque.
     * */
    private void randomAdd(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        if (random < 0.5) {
            sad.addFirst(i);
            ads.addFirst(i);
            message += "\naddFirst(" + i + ")";
        } else {
            sad.addLast(i);
            ads.addLast(i);
            message += "\naddLast(" + i + ")";
        }
    }

    /** Given uniformly distributed random double between 0 and 1,
     * randomly adds Integer to deque.
     * */
    private void randomRemove(double random, Integer i, StudentArrayDeque<Integer> sad, ArrayDequeSolution<Integer> ads) {
        Integer expected;
        Integer actual;
        if (random < 0.5) {
            expected = ads.removeFirst();
            actual = sad.removeFirst();
            message += "\nremoveFirst()";
        } else {
            expected = ads.removeLast();
            actual = sad.removeLast();
            message += "\nremoveLast()";
        }
        assertEquals(message, expected, actual);
    }

    @Test
    public void testRandomized() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (Integer i = 0; i < nCall; i += 1) {
            if (sad.isEmpty()) {
                double random = StdRandom.uniform();
                randomAdd(random, i, sad, ads);
            } else {
                double random1 = StdRandom.uniform();
                double random2 = StdRandom.uniform();
                if (random1 < 0.5) {
                    randomAdd(random2, i, sad, ads);
                } else {
                    randomRemove(random2, i, sad, ads);
                }
            }
        }

    }

}
