// 有一个机器人的位于一个 m × n 个网格左上角。
// 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
// 问有多少条不同的路径

// 1. 确定状态: f[i][j] 走到坐标(i,j)的路径数
// 2. 转移方程: f[i][j] = f[i-1][j] + f[i][j-1]
// 3. 初态边界: f[i][0] = 1, f[0][j] = 1
// 4. 顺序计算: row by row, col by col

public class UniquePath {
  public int uniquePaths(int m, int n) {
		int[][] f =  new int[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) f[i][j] = 1;
				else f[i][j] = f[i - 1][j] + f[i][j - 1];
			}
		}

		return f[m - 1][n - 1];
	}
}
