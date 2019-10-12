import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// lc56

// Given a collection of intervals, merge all overlapping intervals.
// Input:  [(1,3),(2,6),(8,16),(15,18)]
// Output: [(1,6),(8,18)]
// Challenge O(n log n) time and O(1) extra space.

public class Interval {
	int start;
	int end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

// 也可以用扫描线做
public class MergeIntervals {
  public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1) 
			return intervals;

		Collections.sort(intervals, (a, b) -> {
			if (a.start != b.start) return a.start - b.start;
      return a.end - b.end;
		});

		List<Interval> results = new ArrayList<>();

		// prefer这个答案，用start end记载，更清晰
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

		for (int i = 1; i < intervals.size(); i++) {
      Interval current = intervals.get(i);
      
      // 无重叠，加入result
      if (current.start > end) {
        results.add(new Interval(start, end));
        start = current.start;
        end = current.end;
        continue;
			}
			
			// 有重叠，合并，比较end
      end = Math.max(end, current.end);
		}
				
		// 补最后一个！！！！！！！！！！！！！！！！！！！！！！！！！！
		results.add(new Interval(start, end));

    return results;
  }
}
