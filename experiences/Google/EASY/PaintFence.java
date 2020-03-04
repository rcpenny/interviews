// 我们有一个栅栏，它有n个柱子，现在要给柱子染色，有k种颜色可以染
// 必须保证不存在超过2个相邻的柱子颜色相同，求有多少种染色方案

// 确定状态: f[i] 在 post i的染色方案
// 转移方程: f[i] = f[i - 1] * (k - 1) + f[i - 2] * (k - 1)     f[i - 1] + RED 和 f[i - 2] + RED + RED
// 初态边界: f[0] = 0, f[1] = k, f[2] = k * k
// 计算顺序: 从左往右

// follow up: 优化数组至f[size of 3]
public class PaintFence {
  public int numWays(int n, int k) {
    if (n <= 0) return 0;
    if (n == 1) return k;
    if (n == 2) return k * k;
    
    int[] f = new int[n + 1];
    f[0] = 0;
    f[1] = k;
    f[2] = k * k;

    for (int i = 3; i < n + 1; i++)
      f[i] = f[i - 1] * (k - 1) + f[i - 2] * (k - 1);

    return f[n];
  }
}