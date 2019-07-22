// 给一个正整数 n, 请问最少多少个完全平方数(比如1, 4, 9...)的和等于n。
// 输入: 13 输出: 2 解释: 4 + 9

// 0. 最后一步，子问题：最优策略中最后一个完全平方数
// 1. 确定状态: f[i]表示i最少被分成几个完全平方数
// 2. 转移方程: f[i] = min{f[i - j^2] + 1} (1 <=j^2<=i)
// 3. 初态边界  f[0] = 0
// 4. 顺序计算  f[1] -> f[n]

public class PerfectSqaures {
  public int numSquares(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;

		for (int i = 1; i <= n; i++) {
			f[i] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= i; j++)
				f[i] = Math.min(f[i], f[i - j * j] + 1);
		}

		return f[n];
  }
}
