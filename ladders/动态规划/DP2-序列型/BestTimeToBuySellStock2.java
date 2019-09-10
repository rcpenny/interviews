// 给定一个数组 prices 表示一支股票每天的价格.
// 你可以完成任意次数的交易, 不过你不能同时参与多个交易 
// (也就是说, 如果你已经持有这支股票, 在再次购买之前, 你必须先卖掉它).
// 设计一个算法求出最大的利润.

public class BestTimeToBuySellStock2 {

	// DP思想 对比上题，这里可以有无限次的买入和卖出, 买入 状态之前可拥有 卖出 状态
	public int maxProfit2(int[] prices) {
		if (prices == null || prices.length <= 1) return 0;

		int buy = -prices[0];
		int sell = 0;

		for (int i = 1; i < prices.length; i++) {
			sell = Math.max(sell, buy + prices[i]); // 卖 前 有买
			buy = Math.max(buy, sell - prices[i]);  // 买 前 有卖
		}
		return sell;
	}

  // 贪心的思想，有一个上坡就加上去
  public int maxProfit2_(int[] prices) {
		int max = 0;

    for (int i = 1; i < prices.length; i++)
      if (prices[i] > prices[i - 1])
        max += prices[i] - prices[i - 1];
    
    return max;
  }
}
