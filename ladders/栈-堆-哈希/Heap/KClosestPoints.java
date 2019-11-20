import java.util.Comparator;
import java.util.PriorityQueue;

// 给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
// 按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序

// 输入: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
// 输出: [[1,1],[2,5],[4,4]]
class Point {
	int x;
	int y;
	Point(int x, int y) { 
		this.x = x; 
		this.y = y; 
	}
}

public class KClosestPoints {
  public Point[] kClosest(Point[] points, Point origin, int k) {

		Comparator<Point> pointComparator = new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				int a_orgin = getDistance(a, origin);
				int b_orgin = getDistance(b, origin);
				if (a_orgin != b_orgin) return b_orgin - a_orgin;
				if (a.x != b.x) return b.x - a.x;
				return b.y - a.y;
			}
		};
		PriorityQueue<Point> minheap = new PriorityQueue<>(k, pointComparator);
		Point[] result = new Point[k];

		for (Point point : points) {
			if (minheap.size() < k) {
				minheap.offer(point);
				continue;
			}
			// top k 就这么处理，少于k就不停加，到了k就和peek比，要不要加
			if (pointComparator.compare(point, minheap.peek()) > 0) {
			  minheap.poll();
				minheap.offer(point);
			}
		}

		for (int i = k - 1; i >= 0; i--) result[i] = minheap.poll();
		return result;
	}

	private int getDistance(Point position, Point origin) {
		return (position.x - origin.x) * (position.x - origin.x) + 
			(position.y - origin.y) * (position.y - origin.y);
	}
}
