import java.util.Comparator;
import java.util.List;

// 给定一系列的会议时间间隔，包括起始和结束时间[[s1,e1]，[s2,e2]，…(si < ei)，确定一个人是否可以参加所有会议
// 输入: intervals = [(0,30),(5,10),(15,20)]
// 输出: false
// 解释:
// (0,30), (5,10) 和 (0,30),(15,20) 这两对会议会冲突

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

public class MeetingRoom {
  public boolean canAttendMeetings(List<Interval> intervals) {
    if (intervals == null || intervals.size() <= 1) return true;

    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override public int compare(Interval a, Interval b) {
        return a.start - b.start;
      }
    });

    for (int i = 0; i < intervals.size() - 1; i++) {
      Interval current = intervals.get(i);
      intervals next = intervals.get(i + 1);
      if (current.end > next.start) return false;
    }

    return true;
  }
}