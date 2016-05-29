package LinkedIn;

import java.util.Arrays;

public class NumberInSortedList {
    public static void main(String[] argv) {
        int[] list1 = {6, 7, 0, 1, 2, 3, 4, 5};
        int[] list2 = {3, 4, 5, 6, 7, 0, 1, 2};
        NumberInSortedList number = new NumberInSortedList();
        Arrays.stream(list1).forEach(value -> System.out.println(number.isInListSelf(value, list1)));
        Arrays.stream(list2).forEach(value -> System.out.println(number.isInListSelf(value, list2)));
    }

    // return -1 for nothing
    public int isInListSelf(int targetValue, int[] list) {
        int start = 0;
        int end = list.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list[mid] < list[end]) {
                // 670 1 2345    -> 4
                if (targetValue > list[mid]) {
                    if (targetValue > list[end]) {
                        end = mid - 1;
                    } else if (targetValue < list[end]) {
                        start = mid + 1;
                    } else {
                        return end;
                    }
                } else if (targetValue < list[mid]) {
                    end = mid - 1;
                } else {
                    return mid;
                }
            } else {
                // 345 6 7012
                if (targetValue > list[mid]) {
                    start = mid + 1;
                } else if (targetValue < list[mid]) {
                    if (targetValue > list[end]) {
                        end = mid - 1;
                    } else if (targetValue < list[end]) {
                        start = mid + 1;
                    } else {
                        return end;
                    }
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * Find Minimum in Rotated Sorted Array
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * <p>
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * <p>
     * Find the minimum element.
     * <p>
     * You may assume no duplicate exists in the array.
     * <p>
     * Subscribe to see which companies asked this question
     */
    public int findMinI(int[] nums) {
        return 0;
    }

    /**
     * Find Minimum in Rotated Sorted Array II
     * Follow up for "Find Minimum in Rotated Sorted Array":
     * What if duplicates are allowed?
     * <p>
     * Would this affect the run-time complexity? How and why?
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * <p>
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * <p>
     * Find the minimum element.
     * <p>
     * The array may contain duplicates.
     */
    public int findMinII(int[] nums) {
        return 0;
    }
}
