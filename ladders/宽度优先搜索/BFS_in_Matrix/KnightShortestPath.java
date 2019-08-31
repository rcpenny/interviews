import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * 给定骑士在棋盘上的 初始 位置(一个2进制矩阵 0 表示空 1 表示有障碍物)，
 * 找到到达 终点 的最短路线，返回路线的长度。如果骑士不能到达则返回 -1 。
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class KnightShortestPath {
  private int row;
  private int column;
  private int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
  private int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

  public int shortestPath(boolean[][] grid, Point source, Point destination) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
    row = grid.length;
    column = grid[0].length;

    Queue<Point> queue = new LinkedList<>();
    queue.offer(source);
    int steps = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();      
      for (int i = 0; i < size; i++) {
        Point p = queue.poll();
				if (samePoint(p, destination)) return steps;

        for (int move = 0; move < 8; move++) {
          Point adj = new Point(p.x + dx[move], p.y + dy[move]);
          if (validPoint(adj, grid)) {
            queue.offer(adj);
            // 这个matrix 里面，点visit过后一定要mark访问过
            grid[adj.x][adj.y] = true;
          }
        }
      }
      // 这个steps在size的loop结束外面+1
      steps++;
    }

    return -1;
  }

  private boolean samePoint(Point a, Point b) {
    return a.x == b.x && a.y == b.y;
  }

  private boolean validPoint(Point a, boolean[][] grid) {
    return 0 <= a.x && a.x < row && 0 <= a.y && a.y < column && !grid[a.x][a.y];
  }
}
