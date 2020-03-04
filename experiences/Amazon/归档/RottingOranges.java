import java.util.LinkedList;
import java.util.Queue;

// 在给定的网格中，每个单元格可以有以下三个值之一：

// 值 0 代表空单元格；
// 值 1 代表新鲜橘子；
// 值 2 代表腐烂的橘子。
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

class Point {
  int x;
  int y;
  Point (int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class RottingOranges {
  private final int FRESH = 1;
  private final int ROTTED = 2;

  private final int[] dx = {0, 0, 1, -1};
  private final int[] dy = {1, -1, 0, 0};

  int m;
  int n;

  public int orangesRotting(int[][] grid) {
    m = grid.length;
    n = grid[0].length;

    int minutes = 0;

    int freshOrange = 0;
    Queue<Point> queue = new LinkedList<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int current = grid[i][j];
        if (current == FRESH) freshOrange++;
        else if (current == ROTTED) queue.offer(new Point(i, j));
      }
    }

    if (freshOrange == 0) {
      return 0;
    }
    
    while (!queue.isEmpty()) {
      minutes++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Point p = queue.poll();
        for (int j = 0; j < 4; j++) {
          Point adj = new Point(p.x + dx[j], p.y + dy[j]);
          if (!canRotOrange(grid, adj)) continue;
          freshOrange--;
          if (freshOrange == 0) return minutes;
          queue.offer(new Point(adj.x, adj.y));
          grid[adj.x][adj.y] = ROTTED;
        }
      }
    }

    return -1;
  }

  private boolean canRotOrange(int[][] grid, Point adj) {
    return 0 <= adj.x && adj.x < m && 0 <= adj.y && adj.y < n && grid[adj.x][adj.y] == FRESH;
  }
}
