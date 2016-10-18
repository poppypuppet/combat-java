package LeetCode;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version,
 * all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API
 */
public class FirstBadVersion {
    public static void main(String[] argv) {
        FirstBadVersion fbv = new FirstBadVersion();
        System.out.println(fbv.firstBadVersion(2126753399));
    }

    public int firstBadVersion(int n) {
        if (isBadVersion(1)) {
            return 1;
        }

        int left = 2, right = n;
        while (left < right) {
            int mid = left / 2 + right / 2;
            Boolean curr = isBadVersion(mid);
            Boolean prev = isBadVersion(mid - 1);
            if (!curr) {
                left = mid + 1;
            } else {
                if (!prev) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return left;
    }

    Boolean isBadVersion(int v) {
        return v > 1702766718;
    }
}
