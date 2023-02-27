// 给定 n 种物品, 每种物品都有无限个. 第 i 个物品的体积为 A[i], 价值为 V[i].
// 再给定一个容量为 m 的背包. 问可以装入背包的最大价值是多少?

// 输入: A = [2, 3, 5, 7], V = [1, 5, 2, 4], m = 10
// 输出: 15
// 解释: 装入三个物品 1 (A[1] = 3, V[1] = 5), 总价值 15.

// 1. 确定状态: f[i][w] 用前i种物品 拼出重量w时最大总价值（-1表示拼不出w)
// 2. 转移方程: f[i-1][w] = max{f[i-1][w], f[i][w-A[i-1]] + V[i-1]} 
// 3. 初态边界  
// 4. 顺序计算

// 完全背包
public class Backpack3 {
	public int backPackIII(int[] A, int[] V, int m) {
		int n = A.length;
		int[][] f = new int[n + 1][m + 1];

		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j <= m; ++j) {
				f[i][j] = f[i - 1][j];
				
				if (j - A[i - 1] >= 0) {
					int value = f[i][j - A[i - 1]] + V[i - 1];
					f[i][j] = Math.max(f[i][j], value);
				}
			}
		}

		return f[n][m];
	}
}
