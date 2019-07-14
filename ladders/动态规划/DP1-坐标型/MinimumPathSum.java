// 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径

// 确定状态: f[i][j] 从(0,0)走到(i,j)的路径最小数字sum
// 转移方程: f[i][j] = min{f[i-1][j], f[i][j-1]} + grid[i][j]
// 初态边界: f[0][0] = grid[0][0] i = 0 || j = 0
// 计算顺序:

public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if (grid == null ||  grid.length == 0 || grid[0].length == 0)
			return 0;

		int row = grid.length;
		int col = grid[0].length;

		int[][] f = new int [row][col];
		f[0][0] = grid[0][0];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 && j == 0) continue;
				f[i][j] = Integer.MAX_VALUE;

				if (i > 0) f[i][j] = Math.min(f[i][j], f[i - 1][j] + grid[i][j]);
				if (j > 0) f[i][j] = Math.min(f[i][j], f[i][j - 1] + grid[i][j]);
			}
		}

		return f[row - 1][col - 1];
	}
}
