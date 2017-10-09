public class Lab2Work {
    public static void main(String[] args) {
        int[] array = {46, 22, 7, 58, 91, 55, 31, 84, 12, 78};
        if (findMax(array) == 91) {
            System.out.println("findMax is correct!");
        }
        if (reverseStr("Hello").equals("olleH")) {
            System.out.println("reverseString is correct!");
        }
        if (charFrequency("The quick brown fox jumps over he lazy dog.", 'o') == 4) {
            System.out.println("charFrequency is correct!");
        }
    }


    /**
     * Use facade function findMax to call function maxArray();
     *
     * @param array
     * @return maximum value
     */
    public static int findMax(int[] array) {
        return findmaxRev(array, 0, array.length - 1);
    }

    public static int findmaxRev(int[] array, int start, int end) {
        if (start < end) {
            return Integer.max(array[start], findmaxRev(array, start + 1, end));
        } else
            return array[start];
    }

    /**
     * Reverses a string
     *
     * @param s the string to reverse
     * @return the reversed string
     */

    public static String reverseStr(String s) {
        if (s.length() < 2)
            return s;
        else
            return reverseStr(s.substring(1)) + s.charAt(0);
    }

    /**
     * Returns the number of times c appears in the string str
     *
     * @param str string to test
     * @param c   character to find
     * @return number of times the character appeared in the string
     */
    public static int charFrequency(String str, char c) {
        if (str == null || str.equals("")) {
            return 0;
        } else {
            return (str.charAt(0) == c ? 1 : 0) + charFrequency(str.substring(1), c);
        }
    }
}