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
        return null;
    }
}
