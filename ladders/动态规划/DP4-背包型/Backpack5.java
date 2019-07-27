// 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
// 给出候选物品集合 [1,2,3,3,7] 以及 target 7
// 结果的集合为:[7] [1,3,3]

// 1. 确定状态：	f[i][w] 前i个物品拼出重量w的方式
// 2. 转移方程：	f[i][w] = f[i-1][w] + f[i-1][w-A[i-1]]
// 3. 初态边界： f[0][0] = 1
// 4. 顺序计算

// 可用滚动数组优化，极致优化到single array
public class Backpack5 {
  public int backPackV(int[] nums, int target) {
		int n = nums.length;
		if (n == 0) return 0;

		int[][] f = new int[n + 1][target + 1];
		f[0][0] = 1;
		for (int w = 1; w <= target; w++) f[0][w] = 0;

		// 二维数组画图想像
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= target; w++) {
				// case 1
				f[i][w] += f[i - 1][w];

				// case 2
				if (w - nums[i - 1] >= 0)
					f[i][w] += f[i - 1][w - nums[i - 1]];
			}
		}

		int count = 0;
		for (int i = 1; i <= n; i++)
			count = Math.max(count, f[i][target]);
		
		return count;
	}
}
