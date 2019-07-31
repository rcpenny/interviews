import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Given a collection of intervals, merge all overlapping intervals.
// Input:  [(1,3),(2,6),(8,16),(15,18)]
// Output: [(1,6),(8,18)]
// Challenge O(n log n) time and O(1) extra space.
// lint156

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
		if (intervals == null || intervals.size() <= 1) 
			return intervals;

		Collections.sort(intervals, cpt);

		List<Interval> results = new ArrayList<>();

		// 精髓：在外设置一个last,记住上一个被处理的interval,开始为null
		Interval last = null;

		for (int i = 0; i < intervals.size(); i++) {
			Interval current = intervals.get(i);

			// 无重叠 
			if (last == null || last.end < current.start) {
				results.add(current);
				last = current;
				continue;
			}

			// 有重叠
			last.end = Math.max(last.end, current.end);
		}
		
    return results;
  }
}
