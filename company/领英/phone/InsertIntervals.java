import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// lint30
// 给出一个无重叠的按照区间起始端点排序的区间列表。
// 在列表中插入一个新的区间，你要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间)

// 输入:(2, 5) into [(1,2), (5,9)] 输出: [(1,9)]
// 输入:(3, 4) into [(1,2), (5,9)] 输出: [(1,2), (3,4), (5,9)]

public class InsertIntervals {
	// interval的题目就画个图就清晰了
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();

		int insertIndex = 0;

		for (Interval in : intervals) {
			if (in.end < newInterval.start) {
				result.add(in);
				insertIndex++;
				continue;
			}

			if (in.start > newInterval.end) {
				result.add(in);
				continue;
			}

			newInterval.start = Math.min(newInterval.start, in.start);
			newInterval.end = Math.max(newInterval.end, in.end);
		}

		result.add(insertIndex, newInterval);

		return result;
  }
}
