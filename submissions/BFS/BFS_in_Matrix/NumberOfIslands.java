import java.util.Queue;

/**
 * https://www.lintcode.com/problem/number-of-islands/
 * https://leetcode.com/problems/number-of-islands/
 * 
 * 给一个 01 矩阵，求不同的岛屿的个数
 * 0 代表海，1 代表岛，如果两个 1 相邻，那么这两个 1 属于同一个岛。
 * 我们只考虑上下左右为相邻。
 * 
 * DFS 代码少，但是会stack overflow
 */
class Coordinate {
  int x;
  int y;
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class NumberOfIslands {
  // directions 上下左右
  private int[] dx = {0, 1, 0, -1};
  private int[] dy = {-1, 0, 1, 0};
  private boolean SEA = 0;
  private boolean ISLAND = 1;

  public int numIslands(boolean[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int row = grid.length;
    int col = grid[0].length;
    int islandsCount = 0;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[r][c] == ISLAND) {
          markIslandByBFS(grid, i, j, row, col);
          islandsCount++;
        }
      }
    }

    return islandsCount;
  }

  private void markIslandByBFS(boolean[][] grid, int x, int y, int gridRow, int gridCol) {
    Queue<Coordinate> queue = new LinkedList<Coordinate>();
    queue.offer(new Coordinate(x, y));
    grid[x][y] = SEA;

    while (!queue.isEmpty()) {
      Coordinate coordinate = queue.poll();

      for (int i = 0; i < 4; i++) {
        Coordinate adj = new Coordinate(coordinate.x + dx[i], coordinate.y + dy[i]);
        if (inBound(adj, gridRow, gridCol) && grid[adj.x][adj.y] == ISLAND) {
          queue.offer(adj);
          grid[adj.x][adj.y] = SEA;
        }
      }
    }
  }

  private boolean inBound(Coordinate coordinate, int gridRow, int gridCol) {
    int adjRow = coordinate.x;
    int adjCol = coordinate.y;
    return 0 <= adjRow && adjRow < gridRow && 0 <= adjCol && adjCol < gridCol;
  }
}
