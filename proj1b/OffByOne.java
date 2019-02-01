public class OffByOne implements CharacterComparator {

    /** Returns true for characters that are different by exactly one. */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (Math.abs(diff) == 1) {
            return true;
        }
        return false;
    }

}
