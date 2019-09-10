// 给定数组 prices, 其中第 i 个元素代表某只股票在第 i 天第价格.
// 你最多可以完成 k 笔交易. 问最大的利润是多少?
// 输入: k = 2, prices = [4, 4, 6, 1, 1, 4, 2 ,5] 输出: 6
// 解释: 以 4 买入, 以 6 卖出. 然后再以 1 买入, 以 5 卖出. 利润为 2 + 4 = 6.

public class BestTimeToBuySellStock4 {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if (n <= 1) return 0;

		// k > n/2，变成stock 2 无限次买卖
		if (k > n / 2) {
			int result = 0;
			for (int i = 1; i < n; i++)
				if (prices[i] > prices[i - 1])
					result += prices[i] - prices[i - 1];
			return result;
		}

		// k <= n/2 类比 stock 3
		int[] buy = new int[k];
		for (int i = 0; i < k; i++) 
			buy[i] = Integer.MIN_VALUE;
		buy[i] = -prices[0];

		int[] sell = new int[k];

		for (int i = 0; i < prices.length; i++) {
			int current_price = prices[i];

			for (int j = 0; j < k; j++) {
				if (j == 0)
					buy[j] = Math.max(buy[j], sell[j - 1] - current_price);
				else 
					buy[j] = Math.max(buy[j], sell[j] - current_price);

				sell[j] = Math.max(sell[j], buy[j] + current_price);
			}
		}

		return sell[k - 1];
  }
}
