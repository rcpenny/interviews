import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.lintcode.com/problem/zombie-in-matrix/
 * 
 * 598. 僵尸矩阵
 * 给一个二维网格，每一个格子都有一个值，2 代表墙，1 代表僵尸，0 代表人类
 * 僵尸每天可以将上下左右最接近的人类感染成僵尸，但不能穿墙。
 * 将所有人类感染为僵尸需要多久，如果不能感染所有人则返回 -1。
 */

class Point {
  int x;
  int y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class ZombieInMatrix {
  private final int HUMAN = 0;
  private final int ZOMBIE = 1;
  private final int WALL = 2;

  private int[] dx = {1, -1, 0, 0};
  private int[] dy = {0, 0, -1, 1};

  private int row;
  private int col;

  public int zombie(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

    row = grid.length;
    col = grid[0].length;
    
    int humanCount = 0;
    Queue<Point> queue = new LinkedList<>();

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == HUMAN) humanCount++;
        if (grid[i][j] == ZOMBIE) queue.offer(new Point(i, j)); 
      }
    }

    if (humanCount == 0) return 0;

    int days = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Point p = queue.poll();
        for (int j = 0; j < 4; j++) {
          Point adj = new Point(p.x + dx[j], p.y + dy[j]);
          if (!canInfect(adj, grid)) continue;
            humanCount--;
            if (humanCount == 0) return days;
            grid[adj.x][adj.y] = ZOMBIE;
            queue.offer(adj);
        }
      }
      days++;
    }

    return -1;
  }

  private boolean canInfect(Point a, int[][] grid) {
    return 0 <= a.x && a.x < row && 0 <= a.y && a.y < col 
      && grid[a.x][a.y] != WALL && grid[a.x][a.y] != ZOMBIE;
  }
}
