package hw3.hash;

public class Hint {

    public static void main(String[] args) {
        System.out.println("The powers of 256 in Java are:");
        int x = 1;
        for (int i = 0; i < 10; i += 1) {
            System.out.println(i + "th power: " + x);
            x = x * 256;
        }

        /*
         Results:
         The powers of 256 in Java are:
         0th power: 1
         1th power: 256
         2th power: 65536
         3th power: 16777216
         4th power: 0
         5th power: 0
         6th power: 0
         7th power: 0
         8th power: 0
         9th power: 0
         */
    }

} 
