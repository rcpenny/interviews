// 有n个气球，编号为0到n-1，每个气球都有一个分数，存在nums数组中。
// 每次吹气球i可以得到的分数为 nums[left] * nums[i] * nums[right]，
// left和right分别表示i气球相邻的两个气球。当i气球被吹爆后，其左右两气球即为相邻。
// 要求吹爆所有气球，得到最多的分数。

// 1. 确定状态: f[i][j] i-j的balloon能获取的最大coins
// 2. 转移方程: https://youtu.be/z3hu2Be92UA?t=407
//   f[i][j] = max{f[i][k-1] + f[k+1][j] + a[i-1]a[j+1]a[k]} i<k<j
// 3. 初态边界  f[i][i] = a[i-1]a[i]a[i+1]
// 4. 顺序计算

public class BurstBalloon {
	public int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int n = nums.length;

		//开数组到n+2是为了保证k-1 k+1不溢出
		int[][] dp = new int[n+2][n+2];
		for(int i=1;i<=n;i++){
				int left = i-2 >= 0?nums[i-2]:1;
				int right = i < n?nums[i]:1;
				dp[i][i] = left*nums[i-1]*right;
		}
		
		for(int len = 2;len<=n;len++){
				for(int i=1;i+len-1<=n;i++){
						int j = i+len-1;
						int left = i-2 >= 0?nums[i-2]:1;
						int right = j < n?nums[j]:1;
						dp[i][j] = Integer.MIN_VALUE;
						for(int k=i;k<=j;k++){
								dp[i][j] = Math.max(dp[i][j], dp[i][k-1]+dp[k+1][j]+left*right*nums[k-1]);
						}
				}
		}
		return dp[1][n];
}
}
