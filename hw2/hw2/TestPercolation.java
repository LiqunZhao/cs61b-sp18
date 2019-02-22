package hw2;


import org.junit.Test;
import static org.junit.Assert.*;


public class TestPercolation {

    private Percolation system3By3 = new Percolation(3);

    @Test
    public void testIsOpen() {
        system3By3.open(1, 0);
        assertFalse(system3By3.isOpen(0, 0));
        assertTrue(system3By3.isOpen(1, 0));
    }

    @Test
    public void testIsFull() {
        system3By3.open(1, 0);
        assertFalse(system3By3.isFull(1, 0));
        system3By3.open(0, 0);
        assertTrue(system3By3.isFull(1, 0));
    }

    @Test
    public void testNumberOfOpenSites() {
        assertEquals(0, system3By3.numberOfOpenSites());
        system3By3.open(1, 0);
        assertEquals(1, system3By3.numberOfOpenSites());
        system3By3.open(0, 0);
        assertEquals(2, system3By3.numberOfOpenSites());
    }

    @Test
    public void testPercolates() {
        system3By3.open(0, 0);
        system3By3.open(1, 0);
        system3By3.open(1, 1);
        assertFalse(system3By3.percolates());
        system3By3.open(2, 1);
        assertTrue(system3By3.percolates());
    }

}
