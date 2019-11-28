// "不同的路径" 的跟进问题：
// 现在考虑网格中有障碍物，那样将会有多少条不同的路径？
// 网格中的障碍和空位置分别用 1 和 0 来表示

// 确定状态: f[i][j] 到(i,j)的所有可能性
// 转移方程: f[i][j] = f[i-1][j] + f[i][j-1]
// 初态边界: f[0][0] = 1
// 计算顺序: row by row, col by col

// 考虑改成f, m, n 初始化拎到外面去初始化 
public class UniquePath2 {
	public final int TACKLE = 1;

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
			return 0;

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] f = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
			  // 跳过障碍物并标记0，此处无法到达
				if (obstacleGrid[i][j] == TACKLE) {
					f[i][j] = 0;
					continue;
				}

        // 不写这个最后一步会越界 下面三个if其实应该
			  if (i == 0 && j == 0) {
			    f[i][j] = 1; 
			    continue;
			  }
			  
				// 在第一行 与 第一列
				if (i == 0 && j > 0) {
					f[0][j] = f[0][j - 1];
					continue;
				}
				if (j == 0 && i > 0) {
					f[i][0] = f[i - 1][0];
					continue;
				}

				f[i][j] = f[i - 1][j] + f[i][j - 1];
			}
		}

		return f[m - 1][n - 1];
	}
}
