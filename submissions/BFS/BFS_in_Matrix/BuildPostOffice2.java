import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.lintcode.com/problem/build-post-office-ii/
 * 
 * 给出一个二维的网格，每一格可以代表墙 2 ，房子 1，以及空 0 (用数字0,1,2来表示)
 * 在网格中找到一个位置去建立邮局，使得所有的房子到邮局的距离和是最小的
 * 返回所有房子到邮局的最小距离和，如果没有地方建立邮局，则返回-1
 * 
 * 你不能穿过房子和墙，只能穿过空地
 * 你只能在空地建立邮局
 * 
 * 输入：[
 * 				[0,1,0,0,0],
 * 				[1,0,0,2,1],
 * 				[0,1,0,0,0]
 * 			]
 * 输出：8
 * 解释： 在(1,1)处建立邮局，所有房子到邮局的距离和是最小的
 * 
 * 此处解法用了Point，没有双QUEUE BFS快。
 * 此外从EMPTY出发找HOUSE，如果EMPTY很多的话。效率很低。
 * 
 * https://www.lintcode.com/problem/shortest-distance-from-all-buildings/
 * 用从BUILDING BFS的解法。
 */

 class Point {
	 int x;
	 int y;
	 Point(int x, int y) {
		 this.x = x;
		 this.y = y;
	 }
 }

public class BuildPostOffice2 {
	private final int EMPTY = 0;
	private final int HOUSE = 1;
	private final int WALL = 2;

	private final int[] dx = {0, 0, 1, -1};
	private final int[] dy = {1, -1, 0, 0};

	int row;
	int col;

  public int shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
		row = grid.length;
		col = grid[0].length;

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == EMPTY) result = Math.min(result, bfs(grid, i, j));
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private int bfs(int[][] grid, int x, int y) {
		int sum = 0;
		int step = 0;
		boolean[][] visisted = new boolean[row][col];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		visisted[x][y] = true;

		while (!queue.isEmpty()) {
			step++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point p = queue.poll();
				for (int j = 0; j < 4; j++) {
					Point adj = new Point(p.x + dx[j], p.y + dy[j]);
					if (!canMove(adj, grid, visisted)) continue;
					visisted[adj.x][adj.y] = true;
					if (grid[adj.x][adj.y] == EMPTY) queue.offer(adj);
					if (grid[adj.x][adj.y] == HOUSE) sum = sum + step;
				}
			}
		}

		// 检查是否所有的HOUSE都被visit过
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == HOUSE && !visisted[i][j]) return Integer.MAX_VALUE;
			}
		}

		return sum;
	}

	private boolean canMove(Point p, int[][] grid, boolean[][] visisted) {
		return 0 <= p.x && p.x < row && 0 <= p.y && p.y < col && 
			(grid[p.x][p.y] != WALL) && !visisted[p.x][p.y];
	}
}
