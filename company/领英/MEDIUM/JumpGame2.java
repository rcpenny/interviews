// 给出一个非负整数数组，你最初定位在数组的第一个位置。
// 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。

// 1. 确定状态 f[i] 跳到这个index的最少跳跃次数
// 2. 转移方程 f[i] = min(f[j] + 1) when j + A[j] >= i and j < i and f[j] != Integer.MAX_VALUE;
// 3. 初态边界 f[0] = 0
// 4. 顺序计算 left -> right

public class JumpGame2 {

  public int jump(int[] A) {
    if (A == null || A.length <= 1) return 0;
    
    int n = A.length;
    int[] f = new int[n];

    f[0] = 0;

    for (int i = 1; i < n; i++) {
      f[i] = Integer.MAX_VALUE;

      for (int j = 0; j < i; j++) {
        if (f[j] == Integer.MAX_VALUE || j + A[j] < i) continue;

        f[i] = Math.min(f[i], f[j] + 1);
      }
    }

    return f[n - 1];
  }
}