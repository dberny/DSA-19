import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        int max = 0;
        for (int num : A) {
            if (num > max) {
                max = num;
            }
        }
        max += 100;
        int[] counts = new int[max+1];
        for (int num : A) {
            counts[num+100]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                A[index] = i-100;
                counts[i]--;
                index++;
            }
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        int b = 26;
        LinkedList<String>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();
        for (String str: A) {
            int index = str.charAt(str.length()-1-n)-'a';
            L[index].addLast(str);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            while(!list.isEmpty()) {
                A[j] = list.removeFirst();
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
        for (int i = 0; i < stringLength; i++) {
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
