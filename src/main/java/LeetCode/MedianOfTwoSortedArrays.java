package LeetCode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {
    /**
     * 引用Wikipedia对中位数的定义：
     * 计算有限个数的数据的中位数的方法是：把所有的同类数据按照大小的顺序排列。
     * 如果数据的个数是奇数，则中间那个数据就是这群数据的中位数；
     * 如果数据的个数是偶数，则中间那2个数据的算术平均值就是这群数据的中位数。
     * <p>
     * O(n)的解法比较直观，直接merge两个数组，然后求中间值。
     * O(log(m+n))显然是用二分搜索. 相当于“Kth element in 2 sorted array”的变形。
     * 如果(m+n)为奇数，那么找到“(m+n)/2+1 th element in 2 sorted array”即可。
     * 如果（m+n）为偶数，需要找到(m+n)/2 th 及(m+n)/2+1 th，然后求平均。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if ((m + n) / 2 == 0) {
            return 0;
        } else {
            return 0;
        }
    }

    /**
     * a[0]~a[m/2],a[m/2+1]~a[m-1].
     * section---1,section-------2.
     * b[0]~b[n/2],b[n/2+1]~b[n-1].
     * section---3,section-------4.
     * <p>
     * If (m/2+n/2+1) > k && am/2 > bn/2 , drop Section 2
     * If (m/2+n/2+1) > k && am/2 < bn/2 , drop Section 4
     * If (m/2+n/2+1) < k && am/2 > bn/2 , drop Section 3
     * If (m/2+n/2+1) < k && am/2 < bn/2 , drop Section 1
     * 简单的说，就是或者丢弃最大中位数的右区间，或者丢弃最小中位数的左区间。
     * My implementation cannot search first 2 and last 2;
     */
    private int KthElementIn2SortedArray(int k, int[] nums1, int as, int ae, int[] nums2, int bs, int be) {
        int ah = (as + ae) / 2;
        int bh = (bs + be) / 2;
        System.out.println(String.format("%d,%d,%d;%d,%d,%d", as, ae, ah, bs, be, bh));
        if (ah + bh + 2 > k) {
            if (nums1[ah] > nums2[bh]) {
                return KthElementIn2SortedArray(k, nums1, as, ah, nums2, bs, be);
            } else {
                return KthElementIn2SortedArray(k, nums1, as, ae, nums2, bs, bh);
            }
        } else if (ah + bh + 2 < k) {
            if (nums1[ah] > nums2[bh]) {
                return KthElementIn2SortedArray(k, nums1, as, ae, nums2, bh, be);
            } else {
                return KthElementIn2SortedArray(k, nums1, ah, ae, nums2, bs, be);
            }
        } else {
            return nums1[ah] > nums2[bh] ? nums1[ah] : nums2[bh];
        }
    }

    public static void main(String[] argv) {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3, 5, 6, 8, 10, 11};
        int[] nums2 = {2, 4, 7, 9, 12};
//        System.out.println(m.KthElementIn2SortedArray(11, nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1));

//        for (int i = 3; i < 13; i++) {
//            System.out.println(m.KthElementIn2SortedArray(i, nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1));
//        }
    }
}
