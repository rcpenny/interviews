// 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
// 输入:  [3,4,8,5], backpack size=10  输出:  9

// 1. 确定状态：	f[i][w] 前i个物品能否拼出重量w  TRUE/FALSE
// 2. 转移方程：	f[i][w] = 
//                 f[i-1][w]  前i-1个物品能拼出W，那么前n个物品也能拼出W
//              OR
//                 f[i-1][w-A[i-1]] 前i-1个物品能拼出W-A[i-1]，那么前i个物品刚好拼出W
// 3. 初态边界： f[0][0] = TRUE
// 4. 顺序计算

public class Backpack {
  public int backPack(int m, int[] A) {
		int n = A.length;
		if (n == 0) return 0;

		boolean[][] f = new boolean[n + 1][m + 1];
		f[0][0] = true;
		// 前0个物品拼不出重量w
		for (int w = 1; w <= m; w++) f[0][w] = false;

		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= m; w++) {
				// case 1
				if (f[i - 1][w]) {
					f[i][w] = true;
					continue;
				}

				// case 2
				if (w - A[i - 1] >= 0 && f[i - 1][w - A[i - 1]]) 
					f[i][w] = true;
					continue;
			}
		}

		int max_weight = 0;
		for (int w = 0; w <= m; w++)
			if (f[n][w]) max_weight = w;
		
		return max_weight;
	}
}
