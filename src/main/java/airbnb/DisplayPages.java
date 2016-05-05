package airbnb;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DisplayPages {
    public ArrayList<ArrayList<String>> getPages(String[] source, int n) {
        ArrayList<LinkedHashMap<String, String>> ret = new ArrayList<LinkedHashMap<String, String>>();
        ret.add(new LinkedHashMap<String, String>());
        int start = 0;
        int end = source.length;
        int index = 0;
        while (start < end) {
            LinkedHashMap<String, String> indexes = ret.get(index);
            if (indexes.size() > n) {
                index++;
            } else {
                String line = source[start];
                String id = line.trim().split(",")[0];
                start++;
                if (indexes.containsKey(id)) {
                    boolean placed = false;
                    for (int i = index; i < ret.size(); i++) {
                        LinkedHashMap<String, String> page = ret.get(i);
                        if (!page.containsKey(id)) {
                            page.put(id, line);
                            placed = true;
                            break;
                        }
                    }
                    if (!placed) {
                        LinkedHashMap<String, String> page = new LinkedHashMap<String, String>();
                        page.put(id, line);
                        ret.add(page);
                    }
                } else {
                    indexes.put(id, line);
                }
            }
        }
        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
        for (LinkedHashMap<String, String> page : ret) {
            ans.add(new ArrayList(page.values()));
        }
        return ans;
    }

    public void printPages(ArrayList<ArrayList<String>> pages) {
        int i = 0;
        for (ArrayList<String> list : pages) {
            System.out.println(i++);
            for (String str : list)
                System.out.println(str);
        }
    }
}
