// 现在，假设你分别是 m个 0s 和 n个 1s 的统治者. 
// 另一方面, 有一个只包含 0s 和 1s 的字符串构成的数组.

// 输入：["10", "0001", "111001", "1", "0"] 5 3
// 输出： 4 解释：这里总共有 4 个字符串可以用 5个 0s 和 3个 1s来构成, 
// 它们是 "10", "0001", "1", "0"

// 1. 确定状态 f[i][j][k] 前i个01串最多能有多少个被j个0和k个1组成
// 2. 转移方程 f[i][j][k] = MAX {f[i-1][j][k], f[i-1][j-a[i]][k-b[i]] + 1} (j > =a[i] and k >= b[i])
// 3. 初态边界 f[0][0~m][0~n] = 0
// 4. 顺序计算 

// 是个双背包问题 总承重 0是5， 1是3

public class OneAndZeros {
  public int findMaxForm(String[] A, int m, int n) {
		if (A.length == 0) return 0;
		
		int T = A.length;
		int[] cnt0 = new int[T];
		int[] cnt1 = new int[T];

		int i, j, k;
		for (i = 0; i <= T; i++) {
			cnt0[i] = cnt1[i] = 0;
			char[] s = A[i].toCharArray();
			for (j = 0; j < s.length; j++) {
				if (s[j] == '0') cnt0[i]++;
				else cnt1[i]++;
			}
		}

		int[][][] f = new int[T + 1][m + 1][n + 1];
		for (i = 0; i <= m; i++)
			for (j = 0; j <= n; j++)
				f[0][i][j] = 0;

		for (i = 1; i <= T; i++) {
			for (j = 0; j <= m; j++) {
				for (k = 0; k <= n; k++) {
					// j 0's, k 1's
					// do not take A[i-1]
					f[i][j][k] = f[i - 1][j][k];

					// take A[i - 1]
					if (j >= cnt0[i - 1] && k >= cnt1[i - 1]) {
						int prev = f[i - 1][j - cnt0[i - 1]][k - cnt1[i - 1]] + 1;
						f[i][j][k] = Math.max(f[i][j][k], prev);
					}
				}
			}
		}

		int result = 0;
		for (j = 0; j <= m; j++)
			for (k = 0; k <= n; k++)
				result = Math.max(result, f[T][j][k]);

		return result;
	}
}
