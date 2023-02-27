// 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
// 如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。

// 状态: 有 buy 和 sell 这两种状态
// 转移方程:
// 对于buy来说，buy之后可以sell（进入sell状态），也可以不交易（保持buy状态）
// 对于sell来说，卖出后不再进行股票交易（还在sell状态)

// 手上的钱购买当天的股票后相当于亏损, 也就是说当天买的话意味着损失-prices[i]，
// 当天卖的话意味着增加prices[i]，当天卖出总的收益就是 buy + prices[i]

// 所以我们只要考虑当天买和之前的买哪个收益更高，当天卖和之前的卖哪个收益更高

// buy = max(buy, -price[i]) 哪个负的更少（注意：根据定义 buy 是负数）
// sell = max(sell, prices[i] + buy)

// 边界: 第一天 buy = -prices[0], sell = 0，最后返回 sell 即可

public class BestTimeToBuySellStock {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		
		int sell = 0;
		int buy = -prices[0]; // 把买股票的钱想成负数

		for (int i = 1; i < prices.length; i++) {
			sell = Math.max(sell, prices[i] + buy);
			buy = Math.max(buy, -prices[i]);
		}

		return sell;
	}
}
