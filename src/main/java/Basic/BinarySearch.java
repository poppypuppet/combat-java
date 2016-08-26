package Basic;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] argv) {
        BinarySearch bs = new BinarySearch();
        Integer[] nums = {0, 1, 2, 3, 4, 5, 6};
        Arrays.stream(nums).forEach(i -> System.out.println(bs.search(nums, i)));
        System.out.println(bs.search(nums, 9));
    }

    int search(Comparable[] nums, Comparable e) {
        int res = -1;
        int len = nums.length;

        int start = 0;
        int end = len - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (e.compareTo(nums[mid]) < 0) {
                end = mid - 1;
            } else if (e.compareTo(nums[mid]) > 0) {
                start = mid + 1;
            } else {
                res = mid;
                break;
            }
        }
        return res;
    }
}
