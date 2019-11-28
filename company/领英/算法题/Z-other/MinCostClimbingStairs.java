// 在楼梯上，每一号台阶都有各自的费用，即第 i 号台阶有非负成本cost [i]（台阶从0号索引）。
// 一旦你支付了费用，你可以爬一到两步。 你需要找到最低成本来到达最高层，你可以从索引为0的楼梯开始，也可以从索引为1的楼梯开始

// 1.cost 的总长度范围在 [2, 1000]之间.
// 2.每一个 cost[i] 都是一个在 [0, 999]之间的整数.

// 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
// 输出: 6   解释: 最便宜的方法是从第0号台阶起步，只走费用为1的台阶并且跳过第3号台阶

// 确定状态: f[i] 走前i个台阶的最小花费
// 转移方程: f[i] = min { f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2] } 
// 初态边界: f[0] = 0, f[1] = 0
// 计算顺序:

public class MinCostClimbingStairs {
  public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] f = new int[n + 1];

     f[0] = 0;
     f[1] = 0;

    for (int i = 2; i <= n; i++) {
      f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
    }

    return f[n];
  }
}