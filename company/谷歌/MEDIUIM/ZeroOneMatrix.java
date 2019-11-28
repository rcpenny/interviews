import java.util.LinkedList;
import java.util.Queue;

// 给定一个由0和1组成的矩阵，求每个单元格最近的0的距离
// 两个相邻细胞之间的距离是1
class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class ZeroOneMatrix {
	int[] dx = new int[] {0, 0, 1, -1};
	int[] dy = new int[] {1, -1, 0, 0}; 

	public int[][] updateMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0) return null;

		int row = matrix.length;
		int col = matrix[0].length;
		int[][] results = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) results[i][j] = 0;
				else bfs(matrix, i, j, results,new boolean[row][col]);
			}
		}
		return results;
	}

	private void bfs(int[][] matrix, int row, int col, int[][] results, boolean[][] visited) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(row, col));
		visited[row][col] = true;

		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) {
				Point tmp = queue.poll();
				for (int j = 0; j < 4; j++) {
					Point adj = new Point(tmp.x + dx[j], tmp.y + dy[j]);
					if (!inBound(adj, matrix)) continue;
					visited[adj.x][adj.y] = true;
					if (matrix[adj.x][adj.y] == 0) {
						results[row][col] = level;
						return;
					} else {
						queue.offer(adj);
					}
				}
			}
		}
	}

	private boolean inBound(Point adj, int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		return 0 <= adj.x && adj.x < row && 0 <= adj.y && adj.y < col;
	}
}
