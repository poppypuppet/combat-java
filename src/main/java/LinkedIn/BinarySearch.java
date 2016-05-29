package LinkedIn;

public class BinarySearch {
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
