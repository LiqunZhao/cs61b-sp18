import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void testFlik() {
        int a = 128;
        int b = 128;
        int c = 129;

        boolean same = Flik.isSameNumber(a, b);
        boolean different = Flik.isSameNumber(a, c);
        assertTrue(same);
        assertFalse(different);
    }

}
