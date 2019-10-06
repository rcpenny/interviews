// 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。每个房屋染不同的颜色费用也不同，
// 你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
// 费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用

// 输入: [[14,2,11],[11,14,5],[14,3,10]]
// 输出: 10
// 解释: 蓝 绿 蓝, 2 + 5 + 3 = 10

// 确定状态: f[i][color]油漆前i栋房子的最后一种颜色为RGB各自的花费
// 转移方程: f[n][1 ~ 3] = Math.min(f[n-1][other 2 colors]) + cost[n][1 ~ 3]
// 初态边界: f[0][0]=f[0][1]=f[0][2]=0 
// 计算顺序:

public class PaintHouse {
	private final int RED = 0;
	private final int BLUE = 1;
	private final int GREEN = 2;

	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0].length != 3)
			return 0;

		int len = costs.length;
		int[][] f = new int[len + 1][3];
		f[0][RED] = f[0][GREEN] = f[0][BLUE] = 0;

		for (int i = 1; i <= len; i++) {
			// iterate current color
			for (int j = 0; j < 3; j++) {
				f[i][j] = Integer.MAX_VALUE; // 为了min的比较，开始设最大

				// iterate prev colors
				for (int k = 0; k < 3; k++) {
					if (j == k) continue; // 去同色
					f[i][j] = Math.min(f[i][j], f[i - 1][k] + costs[i - 1][j]);
				}
			}
		}

		return Math.min(f[len][RED], Math.min(f[len][BLUE], f[len][GREEN]));
	}
}
