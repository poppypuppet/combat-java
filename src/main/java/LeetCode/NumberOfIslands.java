package LeetCode;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid.length == 0) return res;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    searchIsland(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void searchIsland(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        // 搜索该点连通的上下左右
        if (i > 0 && grid[i - 1][j] == '1') searchIsland(grid, i - 1, j);
        if (j > 0 && grid[i][j - 1] == '1') searchIsland(grid, i, j - 1);
        if (i < grid.length - 1 && grid[i + 1][j] == '1') searchIsland(grid, i + 1, j);
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1') searchIsland(grid, i, j + 1);
    }
}
