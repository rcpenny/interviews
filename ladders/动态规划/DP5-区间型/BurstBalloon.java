// 有n个气球，编号为0到n-1，每个气球都有一个分数，存在nums数组中。
// 每次吹气球i可以得到的分数为 nums[left] * nums[i] * nums[right]，
// left和right分别表示i气球相邻的两个气球。当i气球被吹爆后，其左右两气球即为相邻。
// 要求吹爆所有气球，得到最多的分数。

// 1. 确定状态: f[i][j] i-j的balloon能获取的最大coins
// 2. 转移方程: https://youtu.be/z3hu2Be92UA?t=407
//   f[i][j] = max{f[i][k] + f[k][j] + a[i-1]a[j+1]a[k]} i<k<j
// 3. 初态边界  f[i][i] = a[i-1] a[i] a[i+1]
// 4. 顺序计算

@Todo("similar Monster Hunter")
public class BurstBalloon {
  public int maxCoins(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;

		int i, j, k, len;
		int[] coins = new int[n + 2];
		coins[0] = coins[n + 1] = 1;
		for (i = 1; i < n + 1; i++) {
			coins[i] = nums[i - 1];
		}

		n = n + 2;
		int[][] f = new int[n][n];
		for (i = 0; i < n - 1; i++) {
			f[i][i + 1] = 0; // balloon balloon???
		}

		for (len = 3; len <= n; len++) {
			for (i = 0; i + len <= n; i++) {
				j = i + len - 1;
				f[i][j] = 0;
				for (k = i + 1; k < j; k++) {
					f[i][j] = Math.max(f[i][j], f[i][k] + f[k][j] + coins[i] * coins[k] *coins[j]);
				}
			}
		}

		return f[0][n - 1];
	}
}
