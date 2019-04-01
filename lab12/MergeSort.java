import edu.princeton.cs.algs4.Queue;

public class MergeSort {

    /**
     * Removes and returns the smallest item that is in q1 or q2. <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(
            Queue<Item> items) {
        Queue<Queue<Item>> queues = new Queue<>();
        for (Item item : items) {
            Queue<Item> queue = new Queue<>(); // Create single queue
            queue.enqueue(item);
            queues.enqueue(queue); // Enqueue the single queue into the queues to be returned
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> merged = new Queue<>();
        while (!(q1.isEmpty() && q2.isEmpty())) {
            merged.enqueue(getMin(q1, q2));
        }
        return merged;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        Queue<Item> qLeft = new Queue<>();
        Queue<Item> qRight = new Queue<>();
        int leftSize = queues.size() / 2; // Make left queue
        for (int i = 0; i < leftSize; i += 1) {
            qLeft.enqueue(queues.dequeue().dequeue());
        }
        int rightSize = queues.size(); // Make right queue
        for (int i = 0; i < rightSize; i += 1) {
            qRight.enqueue(queues.dequeue().dequeue());
        }
        Queue<Item> q1 = mergeSort(qLeft);
        Queue<Item> q2 = mergeSort(qRight);
        return mergeSortedQueues(q1, q2);
    }

    public static void main(String[] args) {
        Queue<String> languages = new Queue<>();
        languages.enqueue("Python"); // Add my language-learning history
        languages.enqueue("SQL");
        languages.enqueue("Java");
        languages.enqueue("Julia");
        languages.enqueue("JavaScripts");
        languages.enqueue("Lisp??");
        languages.enqueue("Lisp??"); // Checks duplicated
        Queue<String> sortedLanguages = MergeSort.mergeSort(languages);

        // Should print `Python SQL Java Julia JavaScripts Lisp?? Lisp??`
        System.out.println(languages.toString());
        // Should print `Java JavaScript Julia Lisp?? Lisp?? Python SQL`
        System.out.println(sortedLanguages.toString());
    }

}
