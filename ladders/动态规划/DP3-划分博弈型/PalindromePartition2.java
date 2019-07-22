// 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串
// 最少需要分割几次?

// 0. 最后一步，子问题:最优策略中最后一段回文串S[j - N-1]
// 1. 确定状态：f[i] 前i个字符最少划分成f[i]个回文串
// 2. 转移方程： f[i] = min {f[j]+1} 0<=j<i 且 S[j ... i-1]是palidrome
// 3. 初态边界  f[0] = 0
// 4. 顺序计算  f[0] -> f[n]

public class PalindromePartition2 {

	public int minCut(String ss) {
		char[] s = ss.toCharArray();
		int n = s.length;
		if (n == 0) return 0;

		// 用palin[i][j] 表示s[i...j]是否是回文串
		boolean[][] palin = calcPalins(s);
		int[] f = new int[n + 1];
		f[0] = 0;

		for (int i = 1; i <= n; i++) {
			f[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (palin[j][i - 1]) 
					f[i] = Math.min(f[i], f[j] + 1);
			}
		}

		return f[n] - 1;
	}

	private boolean[][] calcPalins(char[] s) {
		int n = s.length;
		boolean[][] isPalin = new boolean[n][n];

		// 奇数枚举
		for (int p = 0; p < n; p++) {
			int i = p, j = p;
			while (0 <= i && j < n && s[i] == s[j])
				isPalin[i--][j++] = true;
		}

		// 偶数枚举
		for (int p = 0; p < n - 1; p++) {
			int i = p, j = p + 1;
			while (0 <= i && j < n && s[i] == s[j])
				isPalin[i--][j++] = true;
		}

		return isPalin;
	}
}
