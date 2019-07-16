// Say you have an array for which the ith element is the price of a given stock on day i.
// If you were only permitted to complete at most one transaction
// (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
// Note that you cannot sell a stock before you buy one.

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
