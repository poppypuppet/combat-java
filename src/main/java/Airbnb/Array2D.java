package Airbnb;

import java.util.ArrayList;
import java.util.Arrays;

public class Array2D<T> {
    Integer row;
    Integer col;
    private ArrayList<ArrayList<T>> data;

    public Array2D(ArrayList<ArrayList<T>> input) {
        data = input;
        row = null;
        col = null;
    }

    public static void main(String[] args) {
        //[[],[2,3],[],[4,5,6,7,8,9],[]]
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
        input.add(new ArrayList<Integer>());
        input.add(new ArrayList<Integer>(Arrays.asList(2, 3)));
        input.add(new ArrayList<Integer>());
        input.add(new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7, 8, 9)));
        input.add(new ArrayList<Integer>());
        Array2D array2D = new Array2D(input);
        while (array2D.hasNext()) {
            System.out.println(array2D.next());
        }
    }

    public boolean hasNext() {
        if (data != null && !data.isEmpty()) {
            for (int i = row != null ? row : 0; i < data.size(); i++) {
                if (!data.get(i).isEmpty()) {
                    for (int j = ((row != null && i == row && col != null) ? col : 0); j < data.get(i).size(); j++) {
                        if (row == null) {
                            row = i;
                        }
                        if (col == null) {
                            col = j;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("Index out of Bound");
        }
        T res = data.get(row).get(col);
        for (int i = row; i < data.size(); i++) {
            if (!data.get(i).isEmpty()) {
                col = row == i ? col + 1 : 0;
                if (col < data.get(i).size()) {
                    row = i;
                    return res;
                }
            }
        }
        row = data.size();
        return res;
    }
}