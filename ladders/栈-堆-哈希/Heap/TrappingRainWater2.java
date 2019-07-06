import java.util.Comparator;
import java.util.PriorityQueue;

/** https://www.lintcode.com/problem/trapping-rain-water-ii  */

// 将矩阵周边的格子都放到堆里，这些格子上面是无法盛水的。
// 每次在堆里挑出一个高度最小的格子 cell，把周围的格子加入到堆里。
// 这些格子被加入堆的时候，计算他们上面的盛水量。

// 盛水量 = cell.height - 这个格子的高度
// 当然如果这个值是负数，盛水量就等于 0

class Cell {
	int x;
	int y;
	int height;
	Cell (int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
	}
}

public class TrappingRainWater2 {
	private int[] dx = new int[] {1, -1, 0, 0};
	private int[] dy = new int[] {0, 0, 1, -1};

	private Comparator<Cell> cellcpt = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			return a.height - b.height;
		}
	};

  public int trapRainWater(int[][] heights) {
		int water = 0;
		if (heights == null || heights.length == 0) return water;

		int row = heights.length;
		int col = heights[0].length;
		boolean[][] visited = new boolean[row][col];
		PriorityQueue<Cell> minheap = new PriorityQueue<Cell>(cellcpt);

		// 将四条边的点加进去，并mark visited
		for (int i = 0; i < row; i++) {
			minheap.offer(new Cell(i, 0, heights[i][0]));
			minheap.offer(new Cell(i, col -1, heights[i][col - 1]));
			visited[i][0] = true;
			visited[i][col - 1] = true;
		}
		for (int i = 0; i < col; i++) {
			minheap.offer(new Cell(0, i, heights[0][i]));
			minheap.offer(new Cell(row - 1, i, heights[row - 1][i]));
			visited[0][i] = true;
			visited[row - 1][i] = true;
		}

		while (!minheap.isEmpty()) {
			Cell cell = minheap.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cell.x + dx[i];
				int ny = cell.y + dy[i];

				if (0 > nx || nx >= row || 0 > ny || ny >= col) continue; 
				if (visited[nx][ny]) continue;

				visited[nx][ny] = true;
				// 因为更矮的cell先被poll了，所以新cell计算的是四个方向最矮的一个cell，没毛病
				minheap.offer(new Cell(nx, ny, Math.max(cell.height, heights[nx][ny])));
				water = water + Math.max(0, cell.height - heights[nx][ny]);
			}
		}
		return water;
	}
}
