package LeetCode;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {
    /**
     * 直观思路显然是双指针i, j同时扫描A, B，选min(A[i], B[j])作为下一个元素插入。但是只能利用A后面的空间来插入，这样就很不方便了。
     * 反向思路，merge后的数组一共有m+n个数。i, j从A, B尾部扫描，选max(A[i], B[j])插入从m+n起的尾部。这样也可以防止插入到A原来数字的范围内时，overwrite掉A原来的数。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (p1 < 0) {
                nums1[i] = nums2[p2];
                p2--;
            } else if (p2 < 0) {
                nums1[i] = nums1[p1];
                p1--;
            } else if (nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1];
                p1--;
            } else {
                nums1[i] = nums2[p2];
                p2--;
            }
        }
    }

    public static void main(String[] argv) {
        int[] num1 = {1, 5, 9, 0, 0, 0};
        int[] num2 = {2, 6, 8};
        MergeSortedArray m = new MergeSortedArray();
        m.merge(num1, 3, num2, 3);
        Arrays.stream(num1).forEach(e -> System.out.print(e));
    }
}
