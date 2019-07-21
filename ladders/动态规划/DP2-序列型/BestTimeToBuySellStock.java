// 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
// 如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。

public class BestTimeToBuySellStock {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) return 0;

		int max_profit = 0;
		int prev_min = prices[0];

		for (int i = 1; i < prices.length; i++) {
			max_profit = Math.max(max_profit, prices[i] - prev_min);
			prev_min = Math.min(prev_min, prices[i]);
		}

		return max_profit;
	}
}
