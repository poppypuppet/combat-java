package LeetCode;

import Interface.Interval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval {
    public static void main(String[] argv) {
        Interval ni = new Interval(2, 3);
        Interval[] items = {new Interval(1, 5)};
        List<Interval> intervals = Arrays.asList(items);
        InsertInterval ii = new InsertInterval();
        ii.insert(intervals, ni);
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ans = new LinkedList<>();
        Interval pin = newInterval;
        for (Interval interval : intervals) {
            if (interval.end < pin.start) {
                ans.add(interval);
            } else if (pin.end < interval.start) {
                ans.add(pin);
                pin = interval;
            } else {
                pin.start = Math.min(interval.start, pin.start);
                pin.end = Math.max(interval.end, pin.end);
            }
        }
        ans.add(pin);
        return ans;
    }
}
