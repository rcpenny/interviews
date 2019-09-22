import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 给定一系列的会议时间间隔intervals，
// 包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，找到所需的最小的会议室数量

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

// 自定义class的好处就是可以写comparator来排顺序.像heap
class Stamp {
  int time;
  int status; // 1 is meeting begin, 0 is meeting over
  Stamp (int time, int status) {
    this.time = time;
    this.status = status;
  }
}

public class MeetingRooms {
  private final int BEGIN = 1;
  private final int OVER = 0;

	// 解1 pq sort
  public int minMeetingRooms(List<Interval> intervals) {
    int min = 0;
    // 以区间的两端点，建数据并排序
    List<Stamp> stamps = new ArrayList<>();
    for (Interval interval : intervals) {
      stamps.add(new Stamp(interval.start, BEGIN));
      stamps.add(new Stamp(interval.end, OVER));
		}

    Collections.sort(stamps, (a, b) -> {
			if (a.time != b.time) return a.time - b.time;
			return a.status - b.status;
		});

    int count = 0;
    for (Stamp stamp : stamps) {
      if (stamp.status == BEGIN) {
        count++;
        min = Math.max(min, count);
        continue;
      }
      // stamp.status == OVER
    	count--;
    }
    return min;
	}
	

	// 解2 扫描线
	public int minMeetingRooms2(List<Interval> intervals) {
		int min = 0;

		int end_time = 0;
		for (Interval i : intervals)
			end_time = Math.max(end_time, i.end);

		int[] meetings = new int[end_time + 1];

		for (Interval i : intervals) {
			meetings[i.start]++;
			meetings[i.end]--;
		}

		int count = 0;
		for (int m : meetings) {
			count += m;
			min = Math.max(min, count);
		}

		return min;
	}
}
