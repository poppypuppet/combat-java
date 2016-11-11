package LeetCode;

import Tools.Printer;

/**
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * A = [
 * [ 1, 0, 0],
 * [-1, 0, 3]
 * ]
 * <p>
 * B = [
 * [ 7, 0, 0 ],
 * [ 0, 0, 0 ],
 * [ 0, 0, 1 ]
 * ]
 * <p>
 * AB =
 * | 1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * |-1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * | 0 0 1 |
 */

public class SparseMatrixMultiplication {
    public static void main(String[] argv) {
        int[][] A = {
                {1, 0, 0},
                {-1, 0, 3}
        };
        int[][] B = {
                {7, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        SparseMatrixMultiplication smm = new SparseMatrixMultiplication();
        Printer.println(smm.multiply(A, B));
    }

    /**
     * 这道题让我们实现稀疏矩阵相乘，稀疏矩阵的特点是矩阵中绝大多数的元素为0，
     * 而相乘的结果是还应该是稀疏矩阵，即还是大多数元素为0，
     * 那么我们使用传统的矩阵相乘的算法肯定会处理大量的0乘0的无用功，
     * 所以我们需要适当的优化算法，使其可以顺利通过OJ，
     * 我们知道一个ixk的矩阵A乘以一个kxj的矩阵B会得到一个ixj大小的矩阵C，
     * 那么我们来看结果矩阵中的某个元素C[i][j]是怎么来的，
     * 起始是A[i][0]*B[0][j]+A[i][1]*B[1][j]+...+A[i][k]*B[k][j]，
     * 那么为了不重复计算0乘0，我们首先遍历A数组，要确保A[i][k]不为0，才继续计算，
     * 然后我们遍历B矩阵的第k行，如果B[k][j]不为0，我们累加结果矩阵res[i][j]+=A[i][k]*B[k][j];
     * 这样我们就能高效的算出稀疏矩阵的乘法
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = B.length, p = B[0].length;
        int[][] ans = new int[m][p];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i][j] != 0)
                    for (int k = 0; k < p; k++)
                        ans[i][k] += A[i][j] * B[j][k];
        return ans;
    }
}
