package LinkedIn;

public class ListNode {
    public ListNode prev;
    public ListNode next;
    public int key;
    public int value;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public ListNode(int key, int value, ListNode prev, ListNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}