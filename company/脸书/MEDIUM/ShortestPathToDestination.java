import java.util.LinkedList;
import java.util.Queue;

// Given a 2D array representing the coordinates on the map, 
// there are only values 0, 1, 2 on the map. 
// value 0 means that it can pass, 
// value 1 means not passable, 
// value 2 means target place. 
// Starting from the coordinates [0,0],You can only go up, down, left and right. 
// Find the shortest path that can reach the destination, and return the length of the path.

class Point {
  int x;
  int y;
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class ShortestPathToDestination {
  int[] dx = new int[] {0, 0, 1, -1};
  int[] dy = new int[] {1, -1, 0, 0};

  public int shortestPath(int[][] targetMap) {
    if (targetMap == null || targetMap.length == 0) return 0;

    int row = targetMap.length;
    int col = targetMap[0].length;

    Queue<Point> queue = new LinkedList<>();
    if (targetMap[0][0] == 0) queue.offer(new Point(0, 0));
    targetMap[0][0] = 1;

    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      steps++;
      for (int i = 0; i < size; i++) {
        Point current = queue.poll();
        for (int j = 0; j < 4; j++) {
          Point adj = new Point(current.x + dx[j], current.y + dy[j]);
          if (!inBound(adj, row, col) || targetMap[adj.x][adj.y] == 1) continue;
          if (targetMap[adj.x][adj.y] == 2) return steps;
          targetMap[adj.x][adj.y] = 1;
          queue.offer(adj);
        }
      }
    }

    return -1;
  }

  private boolean inBound(Point p, int row, int col) {
    return 0 <= p.x && p.x < row && 0 <= p.y && p.y < col;
  }
}