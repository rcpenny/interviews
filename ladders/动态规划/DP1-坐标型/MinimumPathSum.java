// 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径

// 确定状态: f[i][j] 从(0,0)走到(i,j)的路径最小数字sum
// 转移方程: f[i][j] = min{f[i-1][j], f[i][j-1]} + grid[i][j]
// 初态边界: f[0][0] = grid[0][0]  i = 0 || j = 0
// 计算顺序: row by row, col by col

public class MinimumPathSum {
	// 动态规划解法，开matrix，此题其实可以直接在matrix上操作，就不用开space了
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0)
      return 0;
    
    int m = grid.length;
    int n = grid[0].length;
    
    int[][] f = new int[m][n];
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        f[i][j] = grid[i][j];
        
        if (i == 0 && j == 0) continue;
        
        if (i == 0 && j > 0) {
          f[0][j] += f[0][j - 1];
          continue;
        }
        
        if (j == 0 && i > 0) {
          f[i][0] += f[i - 1][0];
          continue;
        }
        
        f[i][j] += Math.min(f[i - 1][j], f[i][j - 1]);
      }
    }
    
    return f[m - 1][n - 1];
	}
}
