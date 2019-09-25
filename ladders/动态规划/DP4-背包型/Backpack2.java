// 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
// 问最多能装入背包的总价值是多大?

// A[i], V[i], n, m 均为整数
// 你不能将物品进行切分
// 你所挑选的要装入背包的物品的总大小不能超过 m
// 每个物品只能取一次

// 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
// 输出: 9
// 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9 

// 1. 确定状态 f[i][w] 用前i个物品拼出w时的最大总价值(= -1 表示拼不出)
// 2. 转移方程 f[i][w] = max {f[i-1][w], f[i-1][w-A[i-1]] + V[i-1]} f[i-1][w-A[i-1]]不能为-1
// 3. 初态边界 f[0][0] = 0  f[0][1-w] = -1
// 4. 顺序计算 答案枚举所有重量

// 		 w=0  w=1  w=2  w=3 ... w=m
// i=0   0
// i=1  -1
// i=2  -1
// i=3  -1

public class Backpack2 {
  public int backPackII(int m, int[] A, int[] V) {
		int n =  A.length;
		if (n == 0) return 0;

		int[][] f = new int[n + 1][m + 1];
		f[0][0] = 0;
		for (int w = 1; w <= m; w++)
			f[0][w] = -1; // 前0个拼不出重量w

		// 用于打印路径，记录f做选择选了谁
		// int[][] pi = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= m; w++) {
				f[i][w] = f[i - 1][w]; // not using item i - 1
				// pi[i][w] = 0; // 不用 item i - 1

				if (w >= A[i - 1] && f[i-1][w - A[i - 1]] != -1) // using item i - 1
					f[i][w] = Math.max(f[i][w], f[i - 1][w - A[i - 1]] + V[i - 1]);

					// 代表选了 item i - 1
					// if (f[i][w] == f[i - 1][w - A[i - 1]] + V[i - 1])
						// pi[i][w] = 1;
			}
		}

		int result = 0;
		for (int w = 0; w <= m; w++)
			if (f[n][w] != -1) 
				result = Math.max(result, f[n][w]);

		return result;
	}
}
