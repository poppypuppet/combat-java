package LinkedIn;

import java.util.Arrays;

/**
 * Heap-Sort
 * 堆是一种重要的数据结构,为一棵完全二叉树, 底层如果用数组存储数据的话,
 * 假设某个元素为序号为i(i为0到n-1),
 * 如果它有左子树,那么左子树的位置是2i+1,
 * 如果有右子树,右子树的位置是2i+2,
 * 如果有父节点,父节点的位置是(n-1)/2取整.
 * <p>
 * 分为最大堆和最小堆,最大堆的任意子树根节点不小于任意子结点,最小堆的根节点不大于任意子结点.
 * 我们使用的是最大堆.处理的思想和冒泡排序,选择排序非常的类似,一层层封顶,只是最大元素的选取使用了最大堆.
 * 最大堆的最大元素一定在第0位置,构建好堆之后,交换0位置元素与顶即可.
 * 堆排序为原位排序(空间小), 且最坏运行时间是O(n2),是渐进最优的比较排序算法.
 */
public class HeapSort {
    private Comparable[] heap = {};

    public HeapSort(Comparable[] a) {
        Arrays.stream(a).forEach(e -> add2Heap(e));
    }

    private void add2Heap(Comparable e) {
    }
}
