// 有一个机器人的位于一个 m × n 个网格左上角。
// 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
// 问有多少条不同的路径

// 确定状态 最后一步 子问题（与原问题性质相同, 规模更小的问题）
// 设计状态转移方程 dp[i][j] = dp[i-1][j] + dp[i][j-1]
// 确定初始态边界 f[i][0] = 1, f[0][j] = 1
// 按照一定顺序计算
public class UniquePath {
  public int uniquePaths(int m, int n) {
		int[][] dp =  new int[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) dp[i][j] = 1;
				else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[m - 1][n - 1];
	}
}
