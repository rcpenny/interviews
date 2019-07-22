// 给定一个二维矩阵, 每一个格子可能是一堵墙 W,或者 一个敌人 E 或者空 0 (数字 '0'), 
// 返回你可以用一个炸弹杀死的最大敌人数. 炸弹会杀死所有在同一行和同一列没有墙阻隔的敌人。 
// 由于墙比较坚固，所以墙不会被摧毁.

// 确定状态: up[][] down[][] left[][] right[][]
// 转移方程: 
// 初态边界:
// 计算顺序: 根据方向

public class BombEnemy {
  public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int max = 0;
		int m = grid.length, n = grid[0].length;
		int[][] up = new int[m][n]; // 该点向上走，可炸死的人
		int[][] down = new int[m][n];
		int[][] left = new int[m][n];
		int[][] right = new int[m][n];

		int i, j;

		// up, 画图想想，初始位置，last row向上
		for (i = m - 1; i >= 0; i--) {
			for (j = 0; j < n; j++) {
			  up[i][j] = 0;
				if (grid[i][j] == 'W') continue;
				if (grid[i][j] == 'E') up[i][j] = 1;
				if (i + 1 < m) up[i][j] += up[i + 1][j]; 
			}
		}

		// down
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
			  down[i][j] = 0;
				if (grid[i][j] == 'W') continue;
				if (grid[i][j] == 'E') down[i][j] = 1;
				if (i - 1 >= 0) down[i][j] += down[i - 1][j];
			}
		}

		// left  first column向左
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
			  left[i][j] = 0;
				if (grid[i][j] == 'W') continue;
				if (grid[i][j] == 'E') left[i][j] = 1;
				if (j - 1 >= 0) left[i][j] += left[i][j - 1];
			}
		}

		// right
		for (i = 0; i < m; i++) {
			for (j = n - 1; j >= 0; j--) {
			  right[i][j] = 0;
				if (grid[i][j] == 'W') continue;
				if (grid[i][j] == 'E') right[i][j] = 1;
				if (j + 1 < n) right[i][j] += right[i][j + 1];
			}
		}


		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
			  if (grid[i][j] == 'W' || grid[i][j] == 'E') continue; // 漏了这步,bug
				max = Math.max(max, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
			}
		}

		return max;
	}
}
