import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.lintcode.com/problem/shortest-distance-from-all-buildings/
 * 
 * 你想在一个空旷的土地上盖房子，在最短的距离内到达所有的建筑物。
 * 你只能上下左右移动。你得到的是一个二维数组网格的值为0、1或2，其中:
 * 
 * 每一个0标记一个空的土地，你可以自由地通过。
 * 每一个1标记一个你不能通过的建筑物
 * 每一个2标记一个你不能通过的障碍
 */

 class Point {
   int x;
   int y;
   Point(int x, int y) {
     this.x = x;
     this.y = y;
   }
 }

public class ShortestDistanceFromAllBuildings {
  private final int EMPTY = 0;
  private final int BUILDING = 1;
  private final int BLOCK = 2;

  private int[] dx = {0, 0, 1, -1};
  private int[] dy = {1, -1, 0, 0};

  int row;
  int col;

  public int shortestDistance(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return -1;
    }
    
    row = grid.length;
    col = grid[0].length;

    // 所有房子到此Empty点的距离
    int[][] distanceSum = new int[row][col];
    // 这个Empty点 能reach到房子的数量
    int[][] reach = new int[row][col];

    // 统计总building数
    int buildingNumber = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == BUILDING) buildingNumber++;
      }
    }

    // start BFS from BUILDINGS and populate distanceSum & reach
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == BUILDING)  bfs(grid, i, j, distanceSum, reach);
      }
    }

    // 根据 distanceSum[][] 与 reach[][] 找解
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == EMPTY && reach[i][j] == buildingNumber) result = Math.min(result, distanceSum[i][j]); 
      }
    }

    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private void bfs(int[][] grid, int x, int y, int[][] distanceSum, int[][] reach) {
    int steps = 0;
    boolean[][] visisted = new boolean[row][col];

    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(x, y));
    visisted[x][y] = true;
    
    while (!queue.isEmpty()) {
      int size = queue.size();
      steps++;

      for (int i = 0; i < size; i++) {
        Point p = queue.poll();
        for (int j = 0; j < 4; j++) {
          Point adj = new Point(p.x + dx[j], p.y + dy[j]);
          if (!canMove(adj, grid, visisted)) continue;
          // 改状态，改数据，offer
          visisted[adj.x][adj.y] = true;
          distanceSum[adj.x][adj.y] += steps;
          reach[adj.x][adj.y]++;
          queue.offer(adj);
        }
      }
    }
    return;
  }

  // 不越界，不是BLOCK 或 BUILDING，未访问过
  private boolean canMove(Point p, int[][] grid, boolean[][] visisted) {
    return 0 <= p.x && p.x < row && 0 <= p.y && p.y < col 
      && grid[p.x][p.y] != BLOCK && grid[p.x][p.y] != BUILDING && !visisted[p.x][p.y];
  }
}