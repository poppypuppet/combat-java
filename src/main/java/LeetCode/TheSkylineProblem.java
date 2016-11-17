package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 题目比较复杂还有图,查看网页.
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [Li, Ri, Hi]
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ].
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 * A key point is the left endpoint of a horizontal line segment.
 * Note that the last key point, where the rightmost building ends,
 * is merely used to mark the termination of the skyline, and always has zero height.
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:
 * [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 */
public class TheSkylineProblem {
    /**
     * 复杂度
     * 时间 O(NlogN) 空间 O(N)
     * <p>
     * 思路
     * 如果按照一个矩形一个矩形来处理将会非常麻烦，我们可以把这些矩形拆成两个点，一个左上顶点，一个右上顶点。
     * 将所有顶点按照横坐标排序后，我们开始遍历这些点。遍历时，通过一个堆来得知当前图形的最高位置。
     * 堆顶是所有顶点中最高的点，只要这个点没被移出堆，说明这个最高的矩形还没结束。
     * 对于左顶点，我们将其加入堆中。
     * 对于右顶点，我们找出堆中其相应的左顶点，然后移出这个左顶点，同时也意味这这个矩形的结束。
     * 具体代码中，为了在排序后的顶点列表中区分左右顶点，左顶点的值是正数，而右顶点值则存的是负数。
     * <p>
     * 注意
     * 堆中先加入一个零点高度，帮助我们在只有最矮的建筑物时选择最低值
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        // 拆解矩形，构建顶点的列表
        for (int[] b : buildings) {
            // 左顶点存为负数
            height.add(new int[]{b[0], -b[2]});
            // 右顶点存为正数
            height.add(new int[]{b[1], b[2]});
        }
        // 根据横坐标对列表排序，相同横坐标的点纵坐标小的排在前面
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        // 构建堆，按照纵坐标来判断大小
        Queue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
        // 将地平线值0先加入堆中
        pq.offer(0);
        // prev用于记录上次keypoint的高度
        int prev = 0;
        for (int[] h : height) {
            // 将左顶点加入堆中
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                // 将右顶点对应的左顶点移去
                pq.remove(h[1]);
            }
            o
            int cur = pq.peek();
            // 如果堆的新顶部和上个keypoint高度不一样，则加入一个新的keypoint
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }
}
