package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {
    public enum directions {right, down, left, up}

    private int RIGHT, DOWN, LEFT, UP;

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ans;
        }
        DOWN = matrix.length;
        RIGHT = matrix[0].length;
        LEFT = 0;
        UP = 0;
        SmRec(matrix, 0, 0, 0, ans);
        return ans;
    }

    public void SmRec(int[][] matrix, int direction, int x, int y, List<Integer> ans) {
        if (x >= UP && x < DOWN && y >= LEFT && y < RIGHT) {
            ans.add(matrix[x][y]);
            System.out.println(matrix[x][y]);
            if ((direction % 4) == directions.right.ordinal()) {
                if (y + 1 < RIGHT) {
                    SmRec(matrix, direction, x, y + 1, ans);
                } else {
                    UP = x + 1;
                    SmRec(matrix, direction + 1, x + 1, y, ans);
                }
            } else if ((direction % 4) == directions.down.ordinal()) {
                if (x + 1 < DOWN) {
                    SmRec(matrix, direction, x + 1, y, ans);
                } else {
                    RIGHT = y;
                    SmRec(matrix, direction + 1, x, y - 1, ans);
                }
            } else if ((direction % 4) == directions.left.ordinal()) {
                if (y - 1 >= LEFT) {
                    SmRec(matrix, direction, x, y - 1, ans);
                } else {
                    DOWN = x;
                    SmRec(matrix, direction + 1, x - 1, y, ans);
                }
            } else if ((direction % 4) == directions.up.ordinal()) {
                if (x - 1 >= UP) {
                    SmRec(matrix, direction, x - 1, y, ans);
                } else {
                    LEFT = y + 1;
                    SmRec(matrix, direction + 1, x, y + 1, ans);
                }
            }
        }
    }

    public static void main(String[] argv) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        System.out.println(spiralMatrix.spiralOrder(matrix));
    }
}
