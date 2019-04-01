import edu.princeton.cs.algs4.Queue;

public class QuickSort {

    /**
     * Returns a new queue that contains the given queues catenated together. <p>
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex -= 1;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are less than the given pivot.
     * @param equal    An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are equal to the given pivot.
     * @param greater  An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        for (Item item : unsorted) {
            int cmp = item.compareTo(pivot);
            if (cmp < 0) {
                less.enqueue(item);
            } else if (cmp == 0) {
                equal.enqueue(item);
            } else {
                greater.enqueue(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        if (items.size() <= 1) { // Return `items` itself when `items.size() < 0` (Base case)
            return items;
        }

        Item pivot = getRandomItem(items); // Pick random pivot
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        partition(items, pivot, less, equal, greater); // Partition

        Queue<Item> sortedLess = quickSort(less); // Quick sort less part
        Queue<Item> sortedGreater = quickSort(greater); // Quick sort greater part

        /* Catenates the results */
        Queue<Item> sortedItem = catenate(sortedLess, equal);
        sortedItem = catenate(sortedItem, sortedGreater);

        return sortedItem;
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
