import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestSorts {

    @Test
    public void testMergeSortZero() {
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> sortedQueue = MergeSort.mergeSort(queue);
        assertTrue(queue.isEmpty());
        assertTrue(sortedQueue.isEmpty());
    }

    @Test
    public void testQuickSortZero() {
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> sortedQueue = QuickSort.quickSort(queue);
        assertTrue(queue.isEmpty());
        assertTrue(sortedQueue.isEmpty());
    }

    @Test
    public void testMergeSortDuplicated() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 50000; i += 1) {
            queue.enqueue(0);
        }
        for (int i = 0; i < 50000; i += 1) {
            queue.enqueue(1);
        }

        Queue<Integer> sortedQueue = MergeSort.mergeSort(queue);
        for (int i = 0; i < queue.size(); i += 1) {
            assertEquals(queue.dequeue(), sortedQueue.dequeue());
        }
    }

    @Test
    public void testQuickSortDuplicated() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 50000; i += 1) {
            queue.enqueue(0);
        }
        for (int i = 0; i < 50000; i += 1) {
            queue.enqueue(1);
        }

        Queue<Integer> sortedQueue = QuickSort.quickSort(queue);
        for (int i = 0; i < queue.size(); i += 1) {
            assertEquals(queue.dequeue(), sortedQueue.dequeue());
        }
    }

    @Test
    public void testMergeSortSorted() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 100000; i += 1) {
            queue.enqueue(i);
        }

        Queue<Integer> sortedQueue = MergeSort.mergeSort(queue);
        for (int i = 0; i < queue.size(); i += 1) {
            assertEquals(queue.dequeue(), sortedQueue.dequeue());
        }
    }

    @Test
    public void testQuickSortSorted() {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 100000; i += 1) {
            queue.enqueue(i);
        }

        Queue<Integer> sortedQueue = QuickSort.quickSort(queue);
        for (int i = 0; i < queue.size(); i += 1) {
            assertEquals(queue.dequeue(), sortedQueue.dequeue());
        }
    }

}
