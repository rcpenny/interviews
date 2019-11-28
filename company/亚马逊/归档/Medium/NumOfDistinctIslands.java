import java.util.HashSet;

/** 岛屿形状需要不同 */

class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// 用String来模拟BFS轨迹，像不像用String模仿Matrix(sliding puzzle那题)
public class NumOfDistinctIslands {
	private final int ISLAND = 1;
	private final int EMPTY = 0;

	private int[] dx = new int[] {0, 0, -1, 1};
	private int[] dy = new int[] {1, -1, 0, 0};
	private char[] ds = new char[] {'a', 'b', 'c', 'd'};

	private int row;
	private int col;

	public int numberofDistinctIslands(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

		row = grid.length;
		col = grid[0].length;
		Set<String> islands =new HashSet<>();

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (grid[i][j] == ISLAND)
					traverseIslands(grid, i, j, islands);

		return islands.size();
	}

	private void traverseIslands(int[][] grid, int x, int y, Set<String> islands) {
		Queue<Point> queue = new LinkedList<>();
		StringBuilder trace = new StringBuilder();
		grid[x][y] = EMPTY;
		queue.offer(new Point(x, y));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < 4; i++) {
				Point adj = new Point(p.x + dx[i], p.y + dy[i]);
				if (!canMove(adj, grid)) {
					trace.append(' ');
					continue;
				}
				grid[adj.x][adj.y] = EMPTY;
				queue.offer(adj);
				trace.append(ds[i]);
			}
		}

		islands.add(trace.toString());
	}

	// 不越界 && 是岛
	private boolean canMove(Point adj, int[][] grid) {
		return 0 <= adj.x && adj.x < row && 0 <= adj.y && adj.y < col
			&& grid[adj.x][adj.y] == ISLAND;
	}
}
