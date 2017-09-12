package LeetCode;

import Interface.RandomListNode;

import java.util.HashMap;

public class CopyListWithRandomPointer {
    /**
     * 这道链表的深度拷贝题的难点就在于如何处理随机指针的问题，由于每一个节点都有一个随机指针，这个指针可以为空，
     * 也可以指向链表的任意一个节点，如果我们在每生成一个新节点给其随机指针赋值时，都要去遍历原链表的话，OJ上肯定会超时，
     * 所以我们可以考虑用Hash map来缩短查找时间，第一遍遍历生成所有新节点时同时建立一个原节点和新节点的哈希表，
     * 第二遍给随机指针赋值时,查找时间是常数级。
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> dic = new HashMap<>();
        RandomListNode p1 = head;
        RandomListNode p2 = head;
        while (p1 != null) {
            RandomListNode nh = new RandomListNode(p1.label);
            dic.put(p1, nh);
            p1 = p1.next;
        }
        while (p2 != null) {
            RandomListNode np = dic.get(p2);
            np.next = dic.get(p2.next);
            np.random = dic.get(p2.random);
            p2 = p2.next;
        }
        return dic.get(head);
    }
}
