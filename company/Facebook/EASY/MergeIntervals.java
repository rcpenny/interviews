import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Given a collection of intervals, merge all overlapping intervals.
// Input:  [(1,3),(2,6),(8,10),(15,18)]
// Output: [(1,6),(8,10),(15,18)]
// Challenge O(n log n) time and O(1) extra space.

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

public class MergeIntervals {

  private Comparator<Interval> cpt = new Comparator<Interval>() {
    @Override public int compare(Interval a, Interval b) {
      if (a.start != b.start) return a.start - b.start;
      return a.end - b.end;
    }
  };

  public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null || intervals.size() <= 1) return intervals;

    Collections.sort(intervals, cpt);
    List<Interval> results = new ArrayList<>();

    Interval current = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      // 无重叠 (相不相接算不算重叠？)
      if (current.end < intervals.get(i).start) {
        results.add(new Interval(current.start, current.end));
        current = intervals.get(i);
        continue;
      }
      // 有重叠，merge
      // bug点，没有比较max
      current.end = Math.max(current.end, intervals.get(i).end);
    }

    results.add(current);
    return results;
  }
}