/**
 * Array-based double ended queue, which accepts generic types.
 * @Rule: All the method should follow "Deque API" described in
 *  https://sp18.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
 * @Rule: The stating size of array should be 8.
 * @Rule: The amount of memory that this program uses at any given time must be
 *  proportional to the number of items.
 * @Rule: For Arrays of length 16 or more, the usage factor should always be at least 25%.
 */
public class ArrayDeque<T> implements Deque<T> {

    private static int initialCapacity = 8; // The stating length of array
    private static int eFactor = 2; // Expanding factor
    private static int mCapacity = 16; // The minimum capacity for contraction resizing
    private static double mRatio = 0.25; // The minimum usage ratio before contraction
    private static int cFactor = 2; // Contracting factor
    private int capacity; // The length of array
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /** Creates an empty linked array deque */
    public ArrayDeque() {
        capacity = initialCapacity;
        items = (T []) new Object[initialCapacity];
        nextFirst = capacity - 1;
        nextLast = 0;
        size = 0;
    }

    /** Returns true if deque is empty, false otherwise */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque
     * @Rule: Must take constant time;
     */
    @Override
    public int size() {
        return size;
    }

    /** Decreases index according to circular structure. */
    private int oneMinus(int index) {
        if (index == 0) {
            return capacity - 1;
        } else {
            return index - 1;
        }
    }
    /** Increases given index according to circular structure. */
    private int onePlus(int index) {
        if (index == capacity - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    /** Prints the items in the deque from front to last, separated by a space */
    @Override
    public void printDeque() {
        int currentIndex = onePlus(nextFirst);
        while (currentIndex != nextLast) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = onePlus(currentIndex);
        }
        System.out.println();
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such items exists, returns null.
     * @Rule: A single operation must be executed in constant time.
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int indexFromFront = nextFirst + 1 + index;
        if (indexFromFront >= capacity) {
            indexFromFront -= capacity;
        }
        return items[indexFromFront];
    }

    /** Resize the original array to a new array with given capacity. */
    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];

        int currentFirst = onePlus(nextFirst);
        int currentLast = oneMinus(nextLast);

        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(items, currentFirst, newItems, 0, length);
            nextFirst = newCapacity - 1;
            nextLast = length;
        } else {
            int lengthFirsts = capacity - currentFirst;
            int newCurrentFirst = newCapacity - lengthFirsts;
            int lengthLasts = nextLast;
            System.arraycopy(items, currentFirst, newItems, newCurrentFirst, lengthFirsts);
            System.arraycopy(items, 0, newItems, 0, lengthLasts);
            nextFirst = newCapacity - lengthFirsts - 1;
        }

        capacity = newCapacity;
        items = newItems;
    }
    /** Checks whether the array needs expansion, and if so, executes it. */
    private void expand() {
        if (size == capacity) {
            int newCapacity = capacity * eFactor;
            resize(newCapacity);
        }
    }
    /** Checks whether the array needs contraction, and if so, executes it. */
    private void contract() {
        double ratio = (double) size / capacity;
        if (capacity >= mCapacity && ratio < mRatio) {
            int newCapacity = capacity / cFactor;
            resize(newCapacity);
        }
    }

    /** Adds an item of type T to the front of the deque.
     * @Rule: A single operation should be executed in constant time,
     *  except during resizing operation.
     * */
    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size += 1;

        expand(); // Expand if array is full
    }

    /** Adds an item of type T to the back of the deque
     * @Rule: A single operation should be executed in constant time,
     *  except during resizing operation.
     * */
    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size += 1;

        expand(); // Expand if array is full
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null
     * @Rule: A single operation should be executed in constant time,
     *  except during resizing operation.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int currentFirst = onePlus(nextFirst);
        T removed = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null
     * @Rule: A single operation should be executed in constant time,
     *  except during resizing operation.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int currentLast = oneMinus(nextLast);
        T removed = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size -= 1;

        contract(); // Contract array if it only uses less than 25% of memory

        return removed;
    }

}
