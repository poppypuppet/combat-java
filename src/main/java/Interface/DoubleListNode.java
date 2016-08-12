package Interface;

public class DoubleListNode {
    public DoubleListNode prev;
    public DoubleListNode next;
    public int key;
    public int value;

    public DoubleListNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public DoubleListNode(int key, int value, DoubleListNode prev, DoubleListNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}