import java.util.PriorityQueue;

// 给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
// 按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。

// Example
// 例1:

// 输入: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
// 输出: [[1,1],[2,5],[4,4]]
// 例2:

// 输入: points = [[0,0],[0,9]], origin = [3, 1], k = 1
// 输出: [[0,0]]

class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
}

public class KClosestPoints {
  public Point[] kClosest(Point[] points, Point origin, int k) {

		PriorityQueue<Point> heap = new PriorityQueue<>(k, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				int dis_a = getDistance(a, origin);
				int dis_b = getDistance(b, origin);
				if (dis_a != dis_b) {
					return dis_a - dis_b;
				} else if (a.x != b.x) {
					return a.x - b.x;
				}
				return a.y - b.y;
			}
		});

		for (Point point : points) {
			heap.offer(point);
		}

		Point[] result = new Point[k];
		for (int i = 0; i < k; i++) {
			result[i] = heap.poll();
		}
		return result;
	}

	private int getDistance(Point position, Point origin) {
		return (position.x - origin.x) * (position.x - origin.x) + 
			(position.y - origin.y) * (position.y - origin.y);
	}
}
