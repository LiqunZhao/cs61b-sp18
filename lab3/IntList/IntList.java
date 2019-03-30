import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 * [Do not modify this file.]
 */
public class IntList {

    int item;                  // First element of list.
    IntList next;               // Remaining elements of list.

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.item = L.item * L.item;
            L = L.next;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList ret = new IntList(L.item * L.item, null);
        IntList rest = ret;
        L = L.next;
        while (L != null) {
            rest.next = new IntList(L.item * L.item, null);
            rest = rest.next;
            L = L.next;
        }

        return ret;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * elements of B.  May modify items of A. Don't use 'new'.
     * Using recursive structure.
     */
    public static IntList dCatenate(IntList A, IntList B) {
        if (A == null) {
            A = B;
            return A;
        } else {
            A.next = IntList.dCatenate(A.next, B);
            return A;
        }
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * elements of B.  May modify items of A. Don't use 'new'.
     * Using iterative structure.
     */
    public static IntList dCatenateIterative(IntList A, IntList B) {
        IntList ret = A;
        while (A.next != null) {
            A = A.next;
        }
        A.next = B;
        return ret;
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * elements of B.  May NOT modify items of A.  Use 'new'.
     * Using recursive structure.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        } else {
            return new IntList(A.item, catenate(A.next, B));
        }
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * elements of B.  May NOT modify items of A.  Use 'new'.
     * Using iterative structure.
     */
    public static IntList catenateIterative(IntList A, IntList B) {
        IntList ret = new IntList(A.item, null);
        IntList rest = ret;
        while (A.next != null) {
            rest.next = new IntList(A.next.item, null);
            rest = rest.next;
            A = A.next;
        }
        rest.next = B;
        return ret;
    }

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null as input, return null
     *
     * @NOTE It's not possible to make `A` after reversing reversed in an appropriated way,
     * because this class is naked and has not pointer to the head of a list.
     */
    public static IntList reverse(IntList A) {
        if (A == null || A.next == null) {
            return A;
        } else {
            IntList reversed = reverse(A.next);
            A.next.next = A;
            A.next = null;
            return reversed;
        }
    }

    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return item;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.next) {
            p.next = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true if X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.next, L = L.next) {
            if (p.item != L.item) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                return 0;
            }

            tortoise = tortoise.next;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.next) {
            out.format("%s%d", sep, p.item);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }

    /**
     * Given a sorted linked list of items, removes duplicates.
     */
    public static void removeDuplicates(IntList p) {
        if (p == null) {
            return;
        }

        IntList cur = p.next;
        IntList prev = p;
        while (cur != null) {
            if (cur.item == prev.item) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        IntList p = IntList.of(1, 2, 2, 2, 3, 3);
        removeDuplicates(p);
        System.out.println(p.toString());
    }

}
