// 有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。
// 拿到最后一枚硬币的人获胜。请判定 先手玩家 必胜还是必败?
// 若必胜, 返回 true, 否则返回 false.

// 0. 最后一步，子问题
// 1. 确定状态: f[i] 面对i个硬币, 是否先手必胜(f[i] = TRUE / FALSE)
// 2. 转移方程: 
//         TRUE  f[i-1] = false, f[i-2] = false
//         TRUE  f[i-1] = true, f[i-2] = false
// f[i] =    下两步中有一个必胜步，你就必胜
//         TRUE  f[i-1] = false, f[i-2] = true  
//         FALSE f[i-1] = true, f[i-2] = true
//
// 3. 初态边界：f[0] = false, f[1] = true, f[2] = true
// 4. 顺序计算: 0 -> N

public class CoinsInALine {
  public boolean firstWillWin(int n) {
    if (n <= 0) return false;
    if (n <= 2) return true;

    boolean[] f = new boolean[n + 1];
    f[0] = false;
    f[1] = true;
    f[2] = true;

    for (int i = 3; i < n + 1; i++) {
      if (f[i - 1] && f[i - 2]) f[i] = false;
      else f[i] = true;
    }

    return f[n];
  }
}
