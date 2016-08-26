package LeetCode;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target val to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] argv) {
        int[] nums = {1, 3, 5};
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        System.out.println(s.search(nums, 1));
    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            System.out.println(String.format("start=%d, end=%d, mid=%d", start, end, mid));
            System.out.println(String.format("num[start]=%d, num[end]=%d, num[mid]=%d", nums[start], nums[end], nums[mid]));
            if (target == nums[mid]) {
                return mid;
            }
            if (target == nums[start]) {
                return start;
            }
            if (nums[start] > nums[mid]) {
                if (target > nums[mid] && target < nums[start]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            } else {
                if (target > nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return nums[start] == target ? start : -1;
    }
}
