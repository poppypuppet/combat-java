package Basic;

import java.util.Arrays;
import java.util.LinkedList;

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
    private LinkedList<Comparable> heap = new LinkedList<>();

    public static void main(String[] argv) {
        Comparable[] a = {7, 5, 2, 64, 8, 65, 43, 26, 89, 16, 28, 32, 654, 4, 9, 0, 1};
        HeapSort hs = new HeapSort();
        hs.add2Heap(a);
        hs.add2Heap(3);
        System.out.println(hs.getHeap().toString());
        hs.sort();
        System.out.println(hs.getHeap().toString());
        hs.clearHeap();
    }

    public void clearHeap() {
        heap.clear();
    }

    private void add2Heap(Comparable[] a) {
        Arrays.stream(a).forEach(e -> {
            heap.addFirst(e);
            heapify(0, heap.size() - 1);
        });
    }

    private void add2Heap(Comparable e) {
        heap.addFirst(e);
        heapify(0, heap.size() - 1);
    }

    /**
     * @param start start index
     * @param end   end index
     */
    private void heapify(int start, int end) {
        int left = start * 2 + 1;
        int right = start * 2 + 2;
        if (left <= end && heap.get(start).compareTo(heap.get(left)) < 0) {
            swap(start, left);
            heapify(left, end);
        }
        if (right <= end && heap.get(start).compareTo(heap.get(right)) < 0) {
            swap(start, right);
            heapify(right, end);
        }
    }

    private void swap(int x, int y) {
        Comparable e = heap.get(x);
        heap.set(x, heap.get(y));
        heap.set(y, e);
    }

    public LinkedList<Comparable> getHeap() {
        return heap;
    }

    /**
     * 到现在为止我们已经完成了2.2中构想的第一步,A[0]也就是root节点是数组中的最大值.如果直接将root节点取出,会破坏堆的结构,heapsort算法使用了一种非常聪明的方法.
     * 将root节点A[0]和堆中最后一个叶节点(leaf)进行交换,然后取出叶节点.这样,堆中除了以A[0]为root的树破坏了#2 - Heap Property,其他subtree仍然是最大堆.只需对A[0]进行Max-Heapify的操作.
     * 这个过程中将root节点取出的方法也很简单,只需将heapsize-1.
     */
    public void sort() {
        int start = 0;
        int end = heap.size() - 1;
        while (start < end) {
            swap(start, end--);
            heapify(start, end);
        }
    }
}
