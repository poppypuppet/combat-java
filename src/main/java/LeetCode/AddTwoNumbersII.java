package LeetCode;

import Interface.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {
    /**
     * 首先我们要县分别计算出两个链表的长度，然后给稍短一点的链表前面补0，补到和另一个链表相同的长度。
     * 由于要从低位开始相加，而低位是链表的末尾，所以我们采用递归来处理，先遍历到链表的末尾，然后从后面相加，
     * 进位标示符carry用的是引用，这样保证了再递归回溯时值可以正确传递，每次计算的节点后面接上上一次回溯的节点，直到回到首节点完成递归。
     * 最后还是处理最高位的进位问题。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int d1 = getLength(l1);
        int d2 = getLength(l2);
        if (d2 > d1) {
            return addTwoNumbers(l2, l1);
        }
        int diff = d1 - d2;
        if (diff > 0) {
            ListNode nl2 = new ListNode(0);
            ListNode tail = nl2;
            while (diff > 1) {
                tail.next = new ListNode(0);
                tail = tail.next;
            }
            tail.next = l2;
            l2 = nl2;
        }
        ListNode res = null;
        int carry = addTwoNumbersDFS(l1, l2, res);
        if (carry != 0) {
            ListNode newres = new ListNode(carry);
            newres.next = res;
            res = newres;
        }
        return res;
    }

    private int addTwoNumbersDFS(ListNode l1, ListNode l2, ListNode res) {
        if (l1 == null || l2 == null) return 0;
        int carry = addTwoNumbersDFS(l1.next, l2.next, res);
        int sum = l1.val + l2.val + carry;
        ListNode newres = new ListNode(sum % 10);
        newres.next = res;
        res = newres;
        return sum / 10;
    }

    private int getLength(ListNode n) {
        int count = 0;
        while (n != null) {
            n = n.next;
            count++;
        }
        return count;
    }

    public static void main(String[] argv) {
        int[] a1 = {7, 2, 4, 3};
        int[] a2 = {5, 6, 4};
        ListNode l1 = ListNode.creatList(a1);
        ListNode.printList(l1);
        ListNode l2 = ListNode.creatList(a2);
        ListNode.printList(l2);
        AddTwoNumbersII addTwoNumbersII = new AddTwoNumbersII();
        ListNode.printList(addTwoNumbersII.addTwoNumbers(l1, l2));
    }
}
