import java.util.LinkedList;
import java.util.Queue;

/** https://www.lintcode.com/problem/max-area-of-island/description */

class Point {
  int x;
  int y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class MaxAreaOfIslands {
  private final int SEA = 0;
  private final int ISLAND = 1;

  private final int[] dx = {0, 0, 1, -1};
  private final int[] dy = {1, -1, 0, 0};

  private int row;
  private int col;

  public int maxAreaOfIsland(int[][] grid) {
    int maxArea = 0;
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

    row = grid.length;
    col = grid[0].length;
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == ISLAND) {
          maxArea = Math.max(maxArea, traverseIsland(grid, i, j));
        }
      }
    }
    return maxArea;
  }

  private int traverseIsland(int[][] grid, int i, int j) {
    int size = 0;
    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(i, j));
    grid[i][j] = SEA;

    while (!queue.isEmpty()) {
      Point p = queue.poll();
      size++;
      for (int move = 0; move < 4; move++) {
        Point adj = new Point(p.x + dx[move], p.y + dy[move]);
        if (!canMove(adj, grid)) continue;
        grid[adj.x][adj.y] = SEA;
        queue.offer(adj);
      } 
    }
    return size;
  }

  private boolean canMove(Point p, int[][] grid) {
    return 0 <= p.x && p.x < row && 0 <= p.y && p.y < col && grid[p.x][p.y] == ISLAND;
  }
}