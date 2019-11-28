class Interval {
  int start;
  int end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

// 更好的解法：红黑树
class MyCalendar {
  List<Interval> events;
  
  private Comparator<Interval> cpt = new Comparator<Interval>() {
    @Override
    public int compare(Interval a, Interval b) {
      if (a.start != b.start) return a.start - b.start;
      return a.end - b.end;
    }
  };
  
  public MyCalendar() {
    events = new ArrayList<Interval>();
  }
  
  public boolean book(int start, int end) {
    Interval event = new Interval(start, end);
    for (Interval e : events) {
      if (e.start < event.start && e.end > event.start) return false;
      if (e.start >= event.start && e.start < event.end) return false;
    }
    events.add(event);
    Collections.sort(events, cpt);
    return true;
  }
}