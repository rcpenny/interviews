// 给出不同面值的硬币以及总金额. 试写一函数来计算构成该总额的组合数量. 你可以假设每一种硬币你都有无限个
// 输入: amount = 8 和 coins = [2, 3, 8]
// 输出: 3
// 解释:
// 有3种方法:
// 8 = 8
// 8 = 3 + 3 + 2
// 8 = 2 + 2 + 2 + 2

public class CoinChange2 {
  public int change(int amount, int[] coins) {    
    int[] f = new int[amount + 1];
    f[0] = 1;

		// combination sum，所以外层是coins
    for (int coin : coins)
      for (int i = coin; i <= amount; i++)
        f[i] += f[i - coin];

    return f[amount];    
  }
}
