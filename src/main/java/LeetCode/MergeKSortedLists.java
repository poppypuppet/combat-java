package LeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Interface.ListNode;
import Tools.Tools;

public class MergeKSortedLists {
    /**
     * java.lang.Comparable: int compareTo(Object o1)
     * 这个方法用于当前对象与o1对象做对比，返回int值，分别的意思是：
     * positive – 当前对象大于o1
     * zero – 当前对象等于o1
     * negative – 当前对象小于o1
     * java.util.Comparator: int compare(Object o1, Object2)
     * 这个方法用于o1与o2对象做对比，返回int值，分别的意思是：
     * positive – o1大于o2
     * zero – o1等于o2
     * negative – o1小于o2
     */
    Comparator<ListNode> ListNodeComparator = (left, right) -> {
        if (left == null) {
            // 左边没有,选择右边
            return 1;
        }
        else if (right == null) {
            // 右边没有,选择左边
            return -1;
        }
        else {
            // 正数,选择左边
            return left.val - right.val;
        }
    };

    public static void main(String[] argv) {
        MergeKSortedLists m = new MergeKSortedLists();
        ListNode[] lists = {new ListNode(1), new ListNode(3), new ListNode(2), new ListNode(4)};
        Tools.println(m.mergeKLists(lists));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        int k = lists.length;
        Queue<ListNode> heap = new PriorityQueue<>(k, ListNodeComparator);
        Arrays.asList(lists).stream().filter(e -> e != null).forEach(n -> heap.offer(n));
        ListNode pin = new ListNode(0);
        ListNode ans = pin;
        while (!heap.isEmpty()) {
            ListNode n = heap.poll();
            if (n.next != null) heap.offer(n.next);
            pin.next = n;
            pin = n;
        }
        if (pin != null) {
            pin.next = null;
        }
        return ans.next;
    }

    private ListNode mergeDevideAndConquer(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }

        int mid = start + (end - start) / 2;
        ListNode left = mergeDevideAndConquer(lists, start, mid);
        ListNode right = mergeDevideAndConquer(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            }
            else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            tail.next = list1;
        }
        else {
            tail.next = list2;
        }

        return dummy.next;
    }
}