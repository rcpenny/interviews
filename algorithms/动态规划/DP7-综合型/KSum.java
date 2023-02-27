// 给定 n 个不同的正整数，整数 k（k <= n）以及一个目标数字 target。　
// 在这 n 个数里面找出 k 个数，使得这 k 个数的和等于目标数字，求问有多少种方案？

// 1. 确定状态: f[i][k][s] 有多少种方式可以在前i个数中选出k个，和为s
// 2. 转移方程: 
//    f[i][k][s] = f[i - 1][k][s] + f[i - 1][k - 1][s - A(i-1)] || (k >= 1 AND s >= A(i-1))
// 3. 初态边界： f[0][0][0] = 1
// 4. 顺序计算: 答案f[N][K][TARGET];

public class KSum {
  public int  kSum(int A[], int k, int target) {
    int n = A.length;

    int[][][] f = new int[n + 1][k + 1][target + 1];

    for (int i = 0; i < n + 1; i++)
        f[i][0][0] = 1;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k && j <= i; j++) {
        for (int t = 1; t <= target; t++) {
          f[i][j][t] += f[i - 1][j][t];

          if (t >= A[i - 1])
            f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
        }
      }
    }

    return f[n][k][target];
  }
}
