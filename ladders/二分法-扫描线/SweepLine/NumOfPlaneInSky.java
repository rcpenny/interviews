import java.util.ArrayList;
import java.util.Comparator;

// 给出飞机的起飞和降落时间的列表，用序列 interval 表示. 请计算出天上同时最多有多少架飞机？
// 如果多架飞机降落和起飞在同一时刻，我们认为降落有优先权。

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = flag;
    this.end = end;
  }
}

class Point {
  int time;
  int status; // 1 is taking off, 0 is landing
  Point(int time, int status) {
    this.time = time;
    this.status = status;
  }
}

public class NumOfPlaneInSky {
  private final int TAKE_OFF = 1;
  private final int LAND = 0;

  private Comparator<Point> airplaneComparator = new Comparator<Point>() {
    @Override public int compare(Point a, Point b) {
      if (a.time != b.time) return a.time - b.time;
      return a.status - b.status;
    }
  };

  public int countOfAirplanes(List<Interval> airplanes) {
    int max = 0;

    List<Point> points = new ArrayList<>();
    for (Interval interval : airplanes) {
      points.add(new Point(interval.start, TAKE_OFF));
      points.add(new Point(interval.end, LAND));
    }
    
    Collections.sort(points, airplaneComparator);

    int count = 0;
    for (Point point: points) {
      if (point.status == TAKE_OFF) {
        count++;
        max = Math.max(count, max);
        continue;
      }
      count--;
    }

    return max;
  }
}