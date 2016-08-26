package Basic;

import java.util.Arrays;

/**
 * 排序
 * 快速排序
 * time=O(n log n), space=(log n).
 * <p>
 * 快速排序的最直接竞争者是堆排序(Heap-sort).
 * 堆排序通常比快速排序稍微慢,但是最坏情况的运行时间总是O(n log n).快速排序是经常比较快,除了intro-sort变化版本外,仍然有最坏情况性能的机会.
 * 如果事先知道堆排序将会是需要使用的,那么直接地使用堆排序比等待intro-sort再切换到它还要快.
 * 堆排序也拥有重要的特点,仅使用固定额外的空间(堆排序是原地排序),而即使是最佳的快速排序变化版本也需要(log n)的空间.
 * 然而,堆排序需要有效率的随机存取才能变成可行.
 * <p>
 * 快速排序也与归并排序(Merge-sort)竞争.
 * 这是另外一种递归排序算法,但有坏情况O(n log n)运行时间的优势.不像快速排序或堆排序,归并排序是一个稳定排序,
 * 且可以轻易地被采用在链表(linked list)和存储在慢速访问媒体上像是磁盘存储或网络连接存储的非常巨大数列.
 * 尽管快速排序可以被重新改写使用在炼串列上,但是它通常会因为无法随机存取而导致差的基准选择.归并排序的主要缺点,是在最佳情况下需要Ω(n)额外的空间.
 */
public class QuickSort {
    public static void main(String argv[]) {
        Integer[] a = {2, 9, 8, 3, 7, 4};
        QuickSort.sort(a);
        Arrays.stream(a).forEach(value -> System.out.println(value));
    }

    public static void sort(Comparable[] a) {
        if (a.length > 0) {
            quick(a, 0, a.length - 1);
        }
    }

    private static void quick(Comparable[] a, int left, int right) {
        if (left < right) {
            int mid = partition(a, left, right);
            quick(a, left, mid - 1);
            quick(a, mid + 1, right);
        }
    }

    private static int partition(Comparable[] a, int left, int right) {
        int mid = left;
        // mid 即为每次被选中数字index,本次分区后 mid 将处于正确位置.
        Comparable value = a[mid];
        // 将 mid 放入队尾以不影响其他交换
        swap(a, mid, right);
        // 以 left 开始搜索,初始时 left = mid
        while (left < right) {
            // left 即为 mid 潜在可能坐标,
            System.out.println("left right " + a[left] + " " + a[right]);
            // 如果a[left] < val, 说明 left 此时所指的值应该在 mid 左边
            if (a[left].compareTo(value) < 0) {
                // 交换 left 和 mid, 将 mid 后推一位.
                swap(a, mid, left);
                mid++;
            }
            // 继续寻找下一个可能在 mid 左边的数字
            left++;
        }
        // 所有值比value小的数字已经换到mid左边,mid已经找到应有的位置,将value换回来.
        swap(a, mid, right);
        return mid;
    }

    private static void swap(Object[] a, int x, int y) {
        Object temp = a[x];
        a[x] = a[y];
        a[y] = temp;
        System.out.println("swap " + a[x] + " " + a[y]);
    }
}
