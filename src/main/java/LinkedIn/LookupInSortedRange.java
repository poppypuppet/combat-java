package LinkedIn;

public class LookupInSortedRange {
    /**
     * Return the smallest character that is strictly larger than the search character,
     * otherwise return the first character in the string.
     * 二分查找空间: [a,b)
     *
     * @param sortedString : sorted list of letters, sorted in ascending order.
     * @param c            : character for which we are searching.
     *                     Given the following inputs we expect the corresponding output:
     *                     ['c', 'f', 'j', 'p', 'v'], 'a' => 'c'
     *                     ['c', 'f', 'j', 'p', 'v'], 'c' => 'f'
     *                     ['c', 'f', 'j', 'p', 'v'], 'k' => 'p'
     *                     ['c', 'f', 'j', 'p', 'v'], 'z' => 'c' // The wrap around case
     *                     ['c', 'f', 'k'], 'f' => 'k'
     *                     ['c', 'f', 'k'], 'c' => 'f'
     *                     ['c', 'f', 'k'], 'd' => 'f'
     */
    public char findInSortedRange(String sortedString, char c) {
        int bound = sortedString.length() - 1;
        int index = findInSortedRangeBS(sortedString, 0, bound, c);
        return index > bound ? sortedString.charAt(0) : sortedString.charAt(index);
    }

    private int findInSortedRangeBS(String sortedString, int start, int end, char c) {
        while (start < end) {
            int mid_index = (start + end) / 2;
            int mid = sortedString.charAt(mid_index);
            if (c < mid) {
                end = mid_index - 1;
            } else if (c > mid) {
                start = mid_index + 1;
            } else {
                return mid_index + 1;
            }
            System.out.println(start + " " + end);
        }
        return c < sortedString.charAt(start) ? start : start + 1;
    }
}
