package LeetCode;

/**
 * Surrounded Regions
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 从上下左右四个边界往里走,凡是能够与上的'O'都是跟边界接壤的,应该保留.
 * [解题思路]
 * 目标是要找到由X包围起来的O的区域。
 * 首先，外围一圈上的O肯定会保留下来。然后，从外围的O能达到的O也要保留。剩下其他的O就是内部的O。
 * 所以方法就是从外围的一圈进行DFS算法：依次对外圈的“O”做DFS，将其可以到达O临时设置为#。
 * 特殊用例：只有外围轮廓没有内部。比如长或者宽小于2，此时不存在被包围的'X'。
 * <p>
 * 小数据可以过，但是大数据提示Memory Limit Exceeded，代码中唯一用到的就是visited这个map，所以，系统期望的方法应该是不需要辅助空间的。
 * 转换一下思路，真的需要开辟一个map在存储访问信息吗？其实这个可以省掉的，既然已经知道连通区域必须至少一个元素是在四边，
 * 那么一开始直接从四边开始扫描就好了，所以无法connect到得元素都是应该被清除的。所以，算法如下：
 * 1. 从四条边上的O元素开始BFS，所有相连的O都赋个新值，比如‘Y’
 * 2. 扫描整个数组，所有现存的O元素全部置为X，所有Y元素置为O
 */
public class SurroundedRegions {
}
