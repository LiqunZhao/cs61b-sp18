package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author KADOWAKI, Shuhei
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in `node`,
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node node) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return getHelper(key, node.left);
        } else {
            return getHelper(key, node.right);
        }
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in `node` with (KEY, VALUE) added as a key-value mapping.
     * Or if `node` is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node node) {
        if (node == null) {
            size += 1;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = putHelper(key, value, node.left);
        } else {
            node.right = putHelper(key, value, node.right);
        }
        return node;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    private void keySetHelper(Set<K> keySet, Node node) {
        if (node == null) {
            return;
        }
        keySet.add(node.key);
        keySetHelper(keySet, node.left);
        keySetHelper(keySet, node.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        keySetHelper(keySet, root);
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Returns 0 if `node` has no child,
     * 1 if has a single child on left,
     * 2 if has a single child on right,
     * and 3 if has 2 children.
     */
    private int deleteCase(Node node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.right == null) {
            return 1;
        } else if (node.left == null) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Returns a node with the maximum key in a subtree rooted from `node`
     */
    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maxNode(node.right);
        }
    }

    private Node removeHelper(K key, Node node) {
        if (key.compareTo(node.key) == 0) { // Deletion occurs in this `node`
            switch (deleteCase(node)) {
                case 1:         // Case 2 (with a single child on left)
                    return node.left;
                case 2:         // Case 2 (with a single child on right)
                    return node.right;
                case 3:         // Case 3 (with 2 children)
                    Node maxNodeOnLeft = maxNode(node.left);
                    K maxKeyOnLeft = maxNodeOnLeft.key;
                    V maxValueOnLeft = maxNodeOnLeft.value;
                    node.key = maxKeyOnLeft;
                    node.value = maxValueOnLeft;
                    node.left = removeHelper(maxKeyOnLeft, node.left);
                    return node;
                default:        // Case 0 (without any child)
                    return null;
            }
        }
        // Deletion occurs in a subtree rooted from `node`
        if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(key, node.left);
        } else {
            node.right = removeHelper(key, node.right);
        }
        return node;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V removed = get(key);
        if (removed == null) {
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removed;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V removed = get(key);
        if (removed == null || removed != value) {
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removed;
    }

}
