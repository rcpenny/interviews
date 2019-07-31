import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// lint30
// 给出一个无重叠的按照区间起始端点排序的区间列表。
// 在列表中插入一个新的区间，你要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间)

// 输入:(2, 5) into [(1,2), (5,9)] 输出: [(1,9)]
// 输入:(3, 4) into [(1,2), (5,9)] 输出: [(1,2), (3,4), (5,9)]

// 就是加了再merge intervals，（已排序！定位到点再加入，不用自己写comparator了）
public class InsertIntervals {

	private Comparator<Interval> cpt = new Comparator<Interval>() {
		@Override public int compare(Interval a, Interval b) {
			if (a.start != b.start) return a.start - b.start;
			return a.end - b.end;
		}
	};

  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		intervals.add(newInterval);
		if (intervals.size() == 1) 
			return intervals;

		Collections.sort(intervals, cpt);

		List<Interval> result = new ArrayList<>();

		int start = intervals.get(0).start;
		int end = intervals.get(0).end;

		for (int i = 1; i < intervals.size(); i++) {
			Interval next = intervals.get(i);

			// overlap
			if (next.start <= end) {
				end = Math.max(end, next.end);
				continue;
			}

			// no overlap
			result.add(new Interval(start, end));
			start = next.start;
			end = next.end;
		}

		result.add(new Interval(start, end));
		return result;
  }
}
