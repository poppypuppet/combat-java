package Tools;

public class Tools {
    public static void println(Object[][] objs) {
        for (int i = 0; i < objs.length; i++) {
            for (int j = 0; j < objs[i].length; j++) {
                System.out.print(objs[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
