// 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径

// 确定状态: f[i][j] 从(0,0)走到(i,j)的路径最小数字sum
// 转移方程: f[i][j] = min{f[i-1][j], f[i][j-1]} + grid[i][j]
// 初态边界: f[0][0] = grid[0][0]  i = 0 || j = 0
// 计算顺序: row by row, col by col

// follow up : 空间优化 滚动数组
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if (grid == null ||  grid.length == 0 || grid[0].length == 0)
			return 0;

		int m = grid.length, n = grid[0].length;
		int old = 0, now = 0;
		int[][] f = new int [2][n];

		for (int i = 0; i < m; i++) {
		  old = now;      // old开始是0, now是1
		  now = 1 - now;
		  
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
				  f[now][j] = grid[0][0];
				  continue;
				}
				f[now][j] = Integer.MAX_VALUE;

				if (i > 0) f[now][j] = Math.min(f[now][j], f[old][j] + grid[i][j]);
				if (j > 0) f[now][j] = Math.min(f[now][j], f[now][j - 1] + grid[i][j]);
			}
		}

		return f[now][n - 1];
	}
}
