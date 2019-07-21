// 只能买卖两次
// Input : [4,4,6,1,1,4,2,5]
// Output : 6

// 1. 确定状态 最后一步 子问题
//    5个阶段： 1.没买卖过 2. 第一次持有股票 3.买卖过一次  4. 第二次持有股票 5. 买卖过两次
//    f[i][j]表示前i天（第i-1天）结束后，在阶段i的最大获利
// 2. 设计状态转移方程
//    阶段 1， 3, 5 —— 手中无股票
//    f[i][j] = max {f[i-1][j], f[i-1][j-1] + prices[i-1] - prices[i-2]}
//                   昨天没持股票  昨天有股票，今天卖出
//    阶段 2，4
//    f[i][j] = max{f[i-1][j] + prices[i-1][j], f[i-1][j-1]}
//                  昨天就有股票，今天继续持有        今天买入股票
// 3. 确定初始态/边界
//    f[0][1] = 0 (前0天，处于阶段1)
//    f[0][2] = f[0][3] = f[0][4] = f[0][5] = 负无穷
// 4. 按照一定顺序计算

public class BestTimeToBuySellStock3 {

	public int maxProfit(int[] prices) {
		int n = prices.length;
		if (n <= 1) return 0;

		// INITIALIZATION
		int[][] f = new int[n + 1][6];
		f[0][1] = 0;
		for (int i = 1; i <= 5; i++)
			f[0][5] = Integer.MIN_VALUE;

		
		for (int i = 1; i <= n; i++) {
			int j;
			// 阶段 1， 3, 5 —— 手中无股票
			// f[i][j] = max {f[i-1][j], f[i-1][j-1] + prices[i-1] - prices[i-2]}
			//                昨天没持股票  昨天有股票，今天卖出
			for (j = 1; j <= 5; j += 2) {
				f[i][j] = f[i - 1][j];
				if (i > 1 && j > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE)
					f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
			}

			// 阶段 2，4
			// f[i][j] = max{f[i-1][j] + prices[i-1][j], f[i-1][j-1]}
			//               昨天就有股票，今天继续持有        今天买入股票
			for (j = 2; j <= 4; j += 2) {
				f[i][j] = f[i - 1][j - 1];
				if (i > 0 && f[i - 1][j] != Integer.MIN_VALUE)
					f[i][j] = Math.max(f[i][j], f[i - 1][j] + prices[i - 1] - prices[i - 2]);
			}
		}

		int result = 0;
		for (int k = 1; k <= 5; k += 2)
			result = Math.max(result, f[n][k]);
		
		return result;
	}

	// 解法2，分状态的解法太麻烦了
	public int maxProfit2(int[] prices) {
		int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
		int sell1 = 0, sell2 = 0;
		for (int price: prices) {
				buy1 = Math.max(buy1, -price);
				sell1 = Math.max(sell1, buy1 + price);
				buy2 = Math.max(buy2, sell1 - price);
				sell2 = Math.max(sell2, buy2 + price);
		}
		return sell2;
	}
	
	// price         4   4   6   1   1   4   2   5
	// buy1  MIN    -4  -4  -4  -1  -1  -1  -1  -1
	// sell1 0       0   0   2   2   2   3   3   4
	// buy2  MIN    -4  -4  -4   1   1   1   1   1
	// sell2 0       0   0   2   2   2   5   5   6
}

