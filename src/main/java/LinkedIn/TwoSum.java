package LinkedIn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TwoSum {
    private Set<Integer> data = new HashSet<>();

    public void store(int input) {
        data.add(input);
    }

    /**
     * Returns true if there is any pair of numbers in the internal data structure which have
     * For example, if the numbers 1, -2, 3, and 6 had been stored, the method should return
     */
    public boolean test(int test) {
        for (Integer i : data) {

            if (data.contains(test - i)) {
                // 还可能加限制条件不能是i自己
                return true;
            }
        }
        return false;
    }

    /* TreeSet为基本操作（add、remove 和 contains）提供受保证的 log(n) 时间开销。*/
    private Set<Integer> nums = new TreeSet<>();

    public boolean ThreeSum(int sum) {
        ArrayList<Integer> arr = new ArrayList<>(nums);
        int n = arr.size();
        for (int i = 0; i < n - 2; i++) {
            int remain = sum - arr.get(i);
            for (int j = i + 1, k = nums.size() - 1; j < k; ) {
                int now = arr.get(j) + arr.get(k);
                if (now > remain) {
                    k--;
                }
                else if (now < remain) {
                    j++;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }
}
