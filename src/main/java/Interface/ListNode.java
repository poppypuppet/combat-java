package Interface;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public String toString() {
        return String.valueOf(val);
    }

    public static ListNode creatList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);
        ListNode pin = head;
        for (int i = 0; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            pin.next = node;
            pin = pin.next;
        }
        return head.next;
    }

    public static void printList(ListNode n) {
        while (n != null) {
            System.out.print(n.val);
            System.out.print("\t");
            n = n.next;
        }
        System.out.println();
    }
}
