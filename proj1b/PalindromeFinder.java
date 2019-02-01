/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        Palindrome palindrome = new Palindrome();
        In in;

/*        System.out.println("### Palindromes ###");
        in = new In("../library-sp18/data/words.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }

        System.out.println("### Off-By-One Palindromes ###");
        in = new In("../library-sp18/data/words.txt");
        CharacterComparator offByOne = new OffByOne();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByOne)) {
                System.out.println(word);
            }
        }*/

        System.out.println("### Off-By-N Palindromes ###");
        int[] cnts = new int[27];
        for (int i = 0; i < 27; i++) {
            System.out.println("Searching for Off-By-" + i + " Parindromes ...");
            in = new In("../library-sp18/data/words.txt");
            CharacterComparator offByi = new OffByN(i);
            int cnt = 0;
            String longest = "";
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByi)) {
                    if (word.length() > longest.length()) {
                        longest = word;
                    }
                    cnt += 1;
                }
            }
            cnts[i] = cnt;
            System.out.println("Total Count: " + cnt);
            System.out.println("    Longest: " + longest);
        }
        System.out.println("Search has been done !");

        System.out.println("\nResults");
        int max = 0;
        int maxN = 0;
        int totalN = 0;
        for (int i = 0; i < cnts.length; i++) {
            int cnt = cnts[i];
            if (cnt > max) {
                max = cnt;
                maxN = i;
            }
            totalN += cnt;
        }
        System.out.println("N with most palindromes: " + maxN + " (" + max + " found)");
        System.out.println("Total palindromes found: " + totalN);
    }

}
