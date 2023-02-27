import java.util.LinkedList;
import java.util.Queue;

// 给出一串整数流和窗口大小，计算滑动窗口中所有整数的平均值。
// MovingAverage m = new MovingAverage(3);
// m.next(1) = 1 // 返回 1.00000
// m.next(10) = (1 + 10) / 2 // 返回 5.50000
// m.next(3) = (1 + 10 + 3) / 3 // 返回 4.66667
// m.next(5) = (10 + 3 + 5) / 3 // 返回 6.00000

public class MovingAvgFromDataStream {
  Queue<Integer> stream;
  int stream_size;
  double stream_sum; // 设置为double最后不用cast

  public MovingAverage(int size) {
    this.stream = new LinkedList<>();
    this.stream_size = size;
    this.stream_sum = 0;
  }

  public double next(int val) {
    stream_sum = stream_sum + val;
    if (stream.size() == stream_size) {
      stream_sum = stream_sum - stream.poll();
    }
    stream.offer(val);
    return stream_sum / stream.size();
  }
}