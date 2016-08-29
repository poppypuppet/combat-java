package Tools;

import Interface.ListNode;

public class Printer {
    public static void println(Object[] objs) {
        for (int i = 0; i < objs.length; i++) {
            System.out.print(objs[i]);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void println(Object[][] objs) {
        for (int i = 0; i < objs.length; i++) {
            for (int j = 0; j < objs[i].length; j++) {
                System.out.print(objs[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void println(ListNode n) {
        while (n != null) {
            System.out.print(n.val);
            System.out.print(" ");
            n = n.next;
        }
        System.out.println();
    }
}
