package LeetCode;

public class NumMatrix {
    private int[][] dp;
    private boolean empty = true;

    public NumMatrix(int[][] matrix) {
        dp = matrix;
        int m = matrix.length;
        boolean e1 = true;
        boolean e2 = true;
        if (m > 0) {
            e1 = false;
            int n = matrix[0].length;
            if (n > 0) {
                e1 = false;
                for (int i = 1; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        dp[i][j] += dp[i - 1][j];
                    }
                }
                for (int j = 1; j < n; j++) {
                    for (int i = 0; i < m; i++) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }
        empty = e1 && e2;
    }

    public static void main(String[] argv) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 1, 2, 3));
        System.out.println(numMatrix.sumRegion(1, 2, 3, 4));
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (empty) {
            return 0;
        }
        int sum = dp[row2][col2];
        boolean area1 = false;
        boolean area2 = false;
        if (row1 - 1 >= 0) {
            sum -= dp[row1 - 1][col2];
            area1 = true;
        }
        if (col1 - 1 >= 0) {
            sum -= dp[row2][col1 - 1];
            area2 = true;
        }
        if (area1 && area2) {
            sum += dp[row1 - 1][col1 - 1];
        }
        return sum;
    }
}
