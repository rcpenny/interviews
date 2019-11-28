// 这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。
// 每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。
// 费用通过一个nxk 的矩阵给出，
// 比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用

// 输入: costs = [[14,2,11],[11,14,5],[14,3,10]]
// 输出: 10
// 说明: 三个屋子分别使用第1,2,1种颜色，总花费是10

// 确定状态： f[i][j]油漆前i栋房子,第i栋是j颜色的花费
// 转移方程： f[i][j] = min(k!=j) {f[i-1][k]} + cost[i-1][j]
// 初态边界： f[0][k] = 0;

// time O(n * k)
// space is O(nk)
public class PaintHouse2 {
  public int minCostII(int[][] costs) {
		int n = costs.length; // house number
		if (n == 0) return 0;
		int k = costs[0].length; // k colors
		if (k == 0) return 0;

		int[][] f = new int[n + 1][k];
		for (int i = 0; i < k; i++) f[0][i] = 0;

		for (int i = 1; i <= n; i++) {

			// 找到f[i-1][j]中的最小值与次小值
			int min = Integer.MAX_VALUE, min_sec = Integer.MAX_VALUE;
			for (int m = 0; m < k; m++) {
				if (f[i - 1][m] >= min_sec) continue;
				if (f[i - 1][m] < min) {
					min_sec = min;
					min = f[i - 1][m];
				} else {
					min_sec = f[i - 1][m];
				}
			}

			for (int j = 0; j < k; j++) {
				// f[i-1][颜色j]是最小cost，加入min_sec(min_sec是从非j颜色的位置选出来的，没毛病)
				if (f[i - 1][j] == min) f[i][j] = min_sec + costs[i - 1][j];
				else f[i][j] = min + costs[i - 1][j];
			}
		}

		int result = Integer.MAX_VALUE;
		for(int i = 0; i < k; i++)
			result = Math.min(result, f[n][i]);
		
		return result;
	}
}
