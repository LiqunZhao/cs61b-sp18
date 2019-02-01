import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offBy1 = new OffByN(1);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome0Or1() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0));
        assertTrue(palindrome.isPalindrome(s1));
    }

    @Test
    public void testIsPalindromeOdd() {
        String sTrue = "racecar";
        String sFalse = "horse";
        assertTrue(palindrome.isPalindrome(sTrue));
        assertFalse(palindrome.isPalindrome(sFalse));
    }

    @Test
    public void testIsPalindromeEven() {
        String sTrue = "noon";
        String sFalse = "rancor";
        assertTrue(palindrome.isPalindrome(sTrue));
        assertFalse(palindrome.isPalindrome(sFalse));
    }

    @Test
    public void testIsPalindromeOffByOne0Or1() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0, offByOne));
        assertTrue(palindrome.isPalindrome(s1, offByOne));
    }

    @Test
    public void testIsPalindromeOffByOneOdd() {
        String sTrue = "flake";
        String sFalse = "frake";
        assertTrue(palindrome.isPalindrome(sTrue, offByOne));
        assertFalse(palindrome.isPalindrome(sFalse, offByOne));
    }

    @Test
    public void testIsPalindromeOffByOneEven() {
        String sTrue = "abcb";
        String sFalse = "abbb";
        assertTrue(palindrome.isPalindrome(sTrue, offByOne));
        assertFalse(palindrome.isPalindrome(sFalse, offByOne));
    }
    
    @Test
    public void testIsPalindromeOffBy10Or1() {
        String s0 = "";
        String s1 = "a";
        assertTrue(palindrome.isPalindrome(s0, offBy1));
        assertTrue(palindrome.isPalindrome(s1, offBy1));
    }

    @Test
    public void testIsPalindromeOffBy1Odd() {
        String sTrue = "flake";
        String sFalse = "frake";
        assertTrue(palindrome.isPalindrome(sTrue, offBy1));
        assertFalse(palindrome.isPalindrome(sFalse, offBy1));
    }

    @Test
    public void testIsPalindromeOffBy1Even() {
        String sTrue = "abcb";
        String sFalse = "abbb";
        assertTrue(palindrome.isPalindrome(sTrue, offBy1));
        assertFalse(palindrome.isPalindrome(sFalse, offBy1));
    }

}
