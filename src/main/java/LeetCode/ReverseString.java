package LeetCode;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh".
 */
public class ReverseString {
    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        int start = 0, end = arr.length - 1;
        if (end > 0) {
            while (start < end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return new String(arr);
    }

    private void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}
