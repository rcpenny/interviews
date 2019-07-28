// 有n个气球，编号为0到n-1，每个气球都有一个分数，存在nums数组中。
// 每次吹气球i可以得到的分数为 nums[left] * nums[i] * nums[right]，
// left和right分别表示i气球相邻的两个气球。当i气球被吹爆后，其左右两气球即为相邻。
// 要求吹爆所有气球，得到最多的分数。

// 1. 确定状态: f[i][j] i-j的balloon能获取的最大coins
// 2. 转移方程: https://youtu.be/z3hu2Be92UA?t=407
//   f[i][j] = max{f[i][k-1] + f[k+1][j] + a[i-1]a[j+1]a[k]} i<=k<=j
// 3. 初态边界  f[i][i] = a[i-1]a[i]a[i+1]
// 4. 顺序计算

@Todo("tonight")
public class BurstBalloon {
  public int maxCoins(int[] nums) {
	}
}
