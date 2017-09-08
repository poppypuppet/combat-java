package Airbnb;

import LinkedIn.LookupInSortedRange;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DisplayPages {
    public static void main(String[] args) {
        // [host_id,list_id,score,city]
        String[] source = new String[]{
                "1,28,300.1,SanFrancisco",
                "4,5,209.1,SanFrancisco",
                "20,7,208.1,SanFrancisco",
                "23,8,207.1,SanFrancisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,SanFrancisco",
                "6,29,204.1,SanFrancisco",
                "7,20,203.1,SanFrancisco",
                "8,21,202.1,SanFrancisco",
                "2,18,201.1,SanFrancisco",
                "2,30,200.1,SanFrancisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,SanJose",
                "6,25,10.1,Oakland",
                "19,15,9.1,SanJose",
                "3,19,8.1,SanJose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,SanJose",
                "5,6,3.1,SanJose",
                "29,22,2.1,SanJose",
        };

        DisplayPages displayPages = new DisplayPages();
        ArrayList<ArrayList<String>> ret = displayPages.getPages(source, 12);
        displayPages.printPages(ret);

        LookupInSortedRange lookupInSortedRange = new LookupInSortedRange();
        for (char c : "ackz".toCharArray()) {
            System.out.println(lookupInSortedRange.findInSortedRange("cfjpv", c));
        }
    }

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
