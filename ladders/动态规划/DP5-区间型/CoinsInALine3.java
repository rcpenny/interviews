// 有 n 个硬币排成一条线, 第 i 枚硬币的价值为 values[i].
// 两个参赛者轮流从任意一边取一枚硬币, 直到没有硬币为止. 拿到硬币总价值更高的获胜.
// 请判定 第一个玩家 会赢还是会输.

// 输入: [3, 2, 2]
// 输出: true
// 解释: 第一个玩家在刚开始的时候拿走 3, 然后两个人分别拿到一枚 2.

// 1. 确定状态 f[i][j] 先手在面对a[i...j]这些数字时，能得到的最大的与对手的数字差
// 2. 转移方程 f[i][j] = max {a[i] - f[i+1][j], a[j] - f[i][j-1]}
// 3. 初态边界  从小区间开始，f[i][i]= a[i]
// 4. 顺序计算

public class CoinsInALine3 {
  public boolean firstWillWin(int[] values) {
		int n = values.length;
		if (n == 0) return true;
	
		int[][] f = new int[n][n];
		// len = 1
		for (int i = 0; i < n; i++) f[i][i] = values[i];

		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				f[i][j] = Math.max(values[i] - f[i+1][j], values[j] - f[i][j-1]);
			}
		}

		return f[0][n - 1] >= 0;
	}
}
