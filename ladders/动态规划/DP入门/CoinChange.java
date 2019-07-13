// 给出不同面额的硬币以及一个总金额. 写一个方法来计算给出的总金额可以换取的最少的硬币数量. 
// 如果已有硬币的任意组合均无法与总金额面额相等, 那么返回 -1.

// 输入：[1, 2, 5] 11
// 输出： 3
// 解释： 11 = 5 + 5 + 1

// 求最大最小值DP
// 1. 确定状态
//   - 最后一步：最优策略中使用的最后一枚硬币A
//   - 子问题： 最少硬币拼出27 - K
// 2. 转移方程
//		 f(27) = MIN{f(27 - 7) + 1, f(27 - 5) + 1, f(27 - 2) + 1}
// 3. 初始条件/边界情况
//   f(0) = 0 / f(拼不出的价格) = 正无穷
// 4. 计算顺序
//  	f(0), f(1) ... f(27) 

// 每一个步尝试coins.length种硬币 O(amount * coins.length)
public class CoinChange {
  public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];

		dp[0] = 0; // 初始条件，硬币换完面值

		for (int i = 1; i <= amount; i++) {
			// dp[i] = 正无穷表示无法拼出amount i
			dp[i] = Integer.MAX_VALUE;

			for (int j = 0; j < coins.length; j++) {
				// 边界条件：X < 0, X - coin 不存在
				if (i - coins[j] < 0) continue;
				if (dp[i - coins[j]] == Integer.MAX_VALUE) continue;

				// 转移方程： F(11) =  min{f(11 - 1) + 1, f(11 - 2) + 1, f(11 - 5) + 1}
				dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
			}
		}
		return dp[amount] == 0 ? -1 : dp[amount];
	}
}
