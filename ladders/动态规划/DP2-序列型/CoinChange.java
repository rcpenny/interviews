// 给出不同面额的硬币以及一个总金额. 写一个方法来计算给出的总金额可以换取的最少的硬币数量. 
// 如果已有硬币的任意组合均无法与总金额面额相等, 那么返回 -1.

// 输入：[1, 2, 5] 11 输出： 3
// 解释： 11 = 5 + 5 + 1

// 1. 确定状态 f[i] 最少拼出27元的硬币数
// 2. 转移方程 f(27) = MIN{f(27 - 7), f(27 - 5), f(27 - 2)} + 1
// 3. 初始条件/边界情况 f(0) = 0 / f(拼不出的价格) = 正无穷
// 4. 计算顺序 f(0), f(1) ... f(27) 

// 每一个步尝试coins.length种硬币 O(amount * coins.length)
public class CoinChange {
	public int coinChange(int[] coins, int amount) {
		int[] f = new int[amount + 1];
		f[0] = 0;
		
		for (int i = 1; i <= amount; i++) {
			f[i] = Integer.MAX_VALUE; // 正无穷表示无法拼出amount i
			
			for (int coin :coins) {
				if (i - coin < 0) continue;
				if (f[i - coin] == Integer.MAX_VALUE) continue;
				
				f[i] = Math.min(f[i], f[i - coin] + 1);
			}
		}
		
		return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
	}	
}
