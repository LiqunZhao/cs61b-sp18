import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    CharacterComparator offBy0 = new OffByN(0);
    CharacterComparator offBy1 = new OffByN(1);
    CharacterComparator offBy2 = new OffByN(2);

    @Test
    public void testEqualsCharBy0() {
        assertTrue(offBy0.equalChars('a', 'a'));
        assertTrue(offBy0.equalChars('b', 'b'));
        assertFalse(offBy0.equalChars('a', 'b'));
        assertFalse(offBy0.equalChars('b', 'a'));
    }

    @Test
    public void testEqualsCharBy1() {
        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('b', 'a'));
        assertTrue(offBy1.equalChars('&', '%'));
        assertFalse(offBy1.equalChars('a', 'a'));
        assertFalse(offBy1.equalChars('a', 'e'));
        assertFalse(offBy1.equalChars('z', 'a'));
    }

    @Test
    public void testEqualsCharBy2() {
        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('c', 'a'));
        assertFalse(offBy2.equalChars('a', 'a'));
        assertFalse(offBy2.equalChars('a', 'b'));
        assertFalse(offBy2.equalChars('b', 'a'));
    }
}
