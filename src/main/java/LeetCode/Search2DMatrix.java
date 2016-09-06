package LeetCode;

import java.util.Arrays;

public class Search2DMatrix {
    public static void main(String[] argv) {
        Search2DMatrix s2d = new Search2DMatrix();
        int[][] matrix = {{5}, {6}};
        System.out.println(s2d.searchMatrix(matrix, 6));
    }

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (matrix.length == 1) {
            int index = Arrays.binarySearch(matrix[0], target);
            return index >= 0;
        } else {
            int n = matrix[0].length;
            int i = 0, j = n - 1;
            while (i < m && j >= 0) {
                int mid = matrix[i][j];
                if (target > mid) {
                    i++;
                } else if (target < mid) {
                    j--;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

}
