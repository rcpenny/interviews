// 给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
// 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
// 判断你是否能到达数组的最后一个位置

// 输入 : [2,3,1,1,4]
// 输出 : true

// 存在性DP

// 1. 确定状态 f(i) 能不能跳到index i
// 2. 设计状态转移方程 f(i) = (枚举上一个跳到的石头j) [ f(j) and j + a[j] >= i ]
// 3. 确定初始态边界 f(0) = true
// 4. 按照一定顺序计算 从小到大

// 时间复杂度 = 状态数 * 状态转移代价 (n * n)

public class JumpGame {
	public boolean canJump(int[] A) {
		int len = A.length;
		boolean[] dp = new boolean[len];
		dp[0] = true;

		for (int i = 1; i < len; i++) {
			dp[i] = false;
			// 枚举 i 前面的所有位置
			for (int j = 0; j < i; j++) {
				// 在 j 位置可以跳到 i
				if (dp[j] && j + A[j] >= i) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[len - 1];
	}
}
