/**
 * Linked-list based double ended queue, which accepts generic types.
 * @Rule: All the method should follow "Deque API" described in
 *  https://sp18.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
 * @Rule: The amount of memory that this program uses at any given time must be
 *  proportional to the number of items.
 */
public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel; // This is circular sentinel !!
    private int size;

    /** Creates an empty linked list deque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Returns true if deque is empty, false otherwise */
    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.prev == sentinel && size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space */
    @Override
    public void printDeque() {
        Node currentNode = sentinel;
        while (currentNode.next != sentinel) {
            System.out.print(currentNode.next.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    /** Adds an item of type T to the front of the deque.
     * @Rule: A single operation should be executed in constant time.
     * */
    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque
     * @Rule: A single operation should be executed in constant time.
     * */
    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null
     * @Rule: A single operation should be executed in constant time.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null
     * @Rule: A single operation should be executed in constant time.
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }

        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such items exists, returns null.
     * @Rule: not alter the deque !
     * @Rule: Must use iteration !
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node currentNode = sentinel.next;
        while (index != 0) {
            currentNode = currentNode.next;
            index -= 1;
        }
        return currentNode.item;
    }

    /** Helper method for getRecursive */
    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }

        return getRecursiveHelper(currentNode.next, index - 1);
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such items exists, returns null.
     * @Rule: not alter the deque !
     * @Rule: Must use recursion !
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }

    /**
     * @NOTE: I used this main method while developing this class, but commented out this section,
     *  in order to satisfy The Deque API.
     */
    /*public static void main(String[] args) {
        LinkedListDeque<Integer> q = new LinkedListDeque<>();

        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("   size: " + q.size());
        System.out.println("removeFirst: " + q.removeFirst());
        System.out.println("removeLast: " + q.removeLast());
        q.printDeque();

        q.addFirst(1);
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("   size: " + q.size());
        System.out.println("get 0th: " + q.get(0));
        System.out.println("get 1st: " + q.getRecursive(1));
        q.printDeque();

        q.addLast(-1);
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("   size: " + q.size());
        System.out.println("get 0th: " + q.get(0));
        System.out.println("get 1st: " + q.getRecursive(1));
        q.printDeque();

        int removedFirst = q.removeFirst();
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("   size: " + q.size());
        System.out.println("removed: " + removedFirst);
        System.out.println("get 0th: " + q.get(0));
        System.out.println("get 1st: " + q.getRecursive(1));
        q.printDeque();

        int removedLast = q.removeLast();
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("   size: " + q.size());
        System.out.println("removed: " + removedLast);
        System.out.println("get 0th: " + q.get(0));
        System.out.println("get 1st: " + q.getRecursive(1));
        q.printDeque();
    }*/

}
