package LeetCode;

public class SortColors {
    /**
     * 计数排序
     * 记录一下每种有多少,然后直接重新赋值
     */
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            counts[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    /**
     * 题目中还要让只遍历一次数组来求解，那么我需要用双指针来做，分别从原数组的首尾往中心移动。
     * 定义red指针指向开头位置，blue指针指向末尾位置
     * 如果遇到0，则交换该值和red指针指向的值，并将red指针后移一位。
     * 若遇到2，则交换该值和blue指针指向的值，并将blue指针前移一位。
     * 若遇到1，则继续遍历。
     */
    public void sortColors_one(int[] nums) {
        int red = 0;
        int blue = nums.length - 1;
        for (int i = 0; i <= blue; i++) {
            if (nums[i] == 0) {
                swap(nums, i, red++);
            } else if (nums[i] == 2) {
                swap(nums, i--, blue--);
            }
        }
    }

    private void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
