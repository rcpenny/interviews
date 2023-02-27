// 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
// 公共子序列一定是对应的字符按顺序都想等

// 1. 确定状态: f[i][j] A的前i个字符[0...i-1]和B前j个字符[0...j-1]的最长公共子序列长度
// 2. 转移方程: f[i][j] = max{f[i-1][j], f[i][j-1], f[i-1][j-1] | A[i-1]=B[j-1]}
// 3. 初态边界: f[0][j] = 0, f[i][0] = 0
// 4. 顺序计算: 上到下，左到右

public class LongestCommonSubseq {
  public int longestCommonSubsequence(String AA, String BB) {
		char[] A = AA.toCharArray();
		char[] B = BB.toCharArray();

		int m = A.length;
		int n = B.length;

		int[][] f = new int[m + 1][n + 1];
		int i, j;

		// 用来记录选择，打印
		int[][] pi = new int[m + 1][n + 1];

		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					f[i][j] = 0;
					continue;
				}

				f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				
				if (f[i][j] == f[i - 1][j]) pi[i][j] = 1;
				if (f[i][j] == f[i][j - 1]) pi[i][j] = 2; 
				
				if (A[i - 1] == B[j - 1]) { // 比较尾巴这里是关键
					f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
					if (f[i][j] == f[i - 1][j - 1] + 1) pi[i][j] = 3; 
				}
			}
		}

		// print
		char[] res = new char[f[m][n]];
		int p = f[m][n] - 1;
		i = m;
		j = n;
		while (i > 0 && j > 0) {
			if (pi[i][j] == 1) {
				i--; // not using A's tail
			} else if (pi[i][j] == 2) {
				j--;
			} else {
				res[p] = A[i - 1];
				p--;
				i--;
				j--;
			}
		}

		for (p = 0; p < f[m][n]; p++) System.out.println(res[p]);

		return f[m][n];
	}
}
 