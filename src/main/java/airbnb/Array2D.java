package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array2D<T> {
    private List<List<T>> array;
    int row;
    int column;

    public Array2D(List<List<T>> input) {
        array = input;
    }

    public static void main(String[] args) {
        //[[2,3],[],[4,5,6,7,8,9],[]]
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        input.add(Arrays.asList(2, 3));
        input.add(new ArrayList<Integer>());
        input.add(Arrays.asList(4, 5, 6, 7, 8, 9));
        input.add(new ArrayList<Integer>());
        Array2D array2D = new Array2D(input);
        while (array2D.hasNext()) {
            System.out.println(array2D.next());
        }
    }

    public boolean hasNext() {
        return false;
    }

    public int next() {
        return 0;
    }
}