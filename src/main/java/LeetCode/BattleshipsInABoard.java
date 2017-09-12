package LeetCode;

/**
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * <p>
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 */
public class BattleshipsInABoard {
    // 由于board中的战舰之间确保有'.'隔开，因此遍历board，若某单元格为'X'，只需判断其左边和上边的相邻单元格是否也是'X'。
    // 如果左邻居或者上邻居单元格是'X'，则说明当前单元格是左边或者上边战舰的一部分；
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    if (j - 1 >= 0 && board[i][j - 1] == 'X' || i - 1 >= 0 && board[i - 1][j] == 'X') {
                        continue;
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
