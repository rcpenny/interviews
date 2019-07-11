import java.util.ArrayList;
import java.util.Comparator;

// 目前有两个用户的有序在线时间序列，每一个区间记录了该用户的登录时间点x和离线时间点y，
// 请找出这两个用户同时在线的时间段，输出的时间段请从小到大排序。你需要返回一个intervals的列表

// 我们保证在线时间序列的长度 1 <= len <= 1e6。
// 我们保证在线时间序列是合法的，即对于某一个用户的在线时间序列，它的任意两个区间不相交

// 输入：seqA = [(1,2),(5,100)], seqB = [(1,6)]
// 输出：[(1,2),(5,6)]
// 解释：在 (1,2), (5,6) 这两个时间段内，两个用户同时在线

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = flag;
    this.end = end;
  }
}

class Stamp {
  int time;
  boolean online;
  public Stamp(int time, boolean online) {
    this.time = time;
    this.online = online;
  }
}

// 本质：天上出现两架飞机时(count 1 -> 2)开始,有一架降落时(count 2 -> 1)结束.
public class TimeIntersection {
  private final boolean ONLINE = true;
  private final boolean OFFLINE = false;

  private Comparator<Stamp> cpt = new Comparator<Stamp>() {
    @Override public int compare(Stamp a, Stamp b) {
      return a.time - b.time;
    }
  };

  public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
    List<Interval> intersections =new ArrayList<>();
    
    List<Stamp> seqs = new ArrayList<>();
    for (Interval a : seqA) {
      seqs.add(new Stamp(a.start, ONLINE));
      seqs.add(new Stamp(a.end, OFFLINE));
    }
    for (Interval b : seqB) {
      seqs.add(new Stamp(b.start, ONLINE));
      seqs.add(new Stamp(b.end, OFFLINE));
    }
    Collections.sort(seqs, cpt);

    Interval tmp = new Interval(0, 0);
    int online_count = 0;
    for (Stamp i : seqs) {
      if (i.online) {
        online_count++;
        if (online_count == 2) tmp.start = i.time;
        continue;
      }
      online_count--;
      if (online_count == 1) { // 有一个下线了
        tmp.end = i.time;
        intersections.add(new Interval(tmp.start, tmp.end)); // 深度拷贝一下 ：）
      }
    }

    return intersections;
  }
}