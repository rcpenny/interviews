// 有n+1个星球，分别为星球0，星球1，……，星球n
// 每一个星球都有一个传送门，通过传送门可以直接到达目标星球而不经过其他的星球。
// 不过传送门有两个缺点。
// 第一，从星球i通过传送门只能到达编号比i大，且与i的差不超过limit的星球。
// 第二，通过传送门到达星球j，需要cost[j]个金币。
// 现在，流浪剑客斯温到达星球0后身上有m个金币，请问他有多少种方法通过传送门到达星球n？

// 1. 确定状态: f[i][j] 从星球0跳到星球i，还剩j枚金币  有多少种方式
// 2. 转移方程: f[i][j] = sum{f[k][j+cost[i]] || j + cost[i] <= m}   i-limit <= k < i
// 3. 初态边界: f[0][m] = 1, f[0][0] ~ f[0][m-1] = 0    k >= 0   j + cost[i] <= m
// 4. 顺序计算: f[0][0] -> f[0][m]   f[0][0] -> f[n][0]
// 答案：f[n][0] + f[n][1] + ... f[n][m]

public class RogueKnightSven {
  // n plant, m gold
  public long getNumberOfWays(int n, int m, int limit, int[] cost) {
    long[][] f = new long[n + 1][m + 1];

    int i, j, k;

    f[0][m] = 1;
    for (j = 0; j < m; j++) f[0][j] = 0;
    
    for (i = 1; i <= n; i++) {
      for (j = 0; j <= m; j++) {
        f[i][j] = 0;

        if (j + cost[i] > m) continue;

        for (k = i - limit; k < i; k++) {
          if (k < 0) continue;
          f[i][j] += f[k][j + cost[i]];
        }
      }
    }

    long sum = 0;
    for (j = 0; j <= m; j++)
      sum = sum + f[n][j];

    return sum;
  }
}