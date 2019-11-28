// 给一字符串 s, 找出在 s 中的最长回文子序列的长度. 你可以假设 s 的最大长度为 1000.
// 输入： "bbbab" 输出： 4
// 解释： 一个可能的最长回文序列为 "bbbb"

// leet516
// O(N^2)

public class LongestPalinSuseq {
  public int longestPalindromeSubseq(String ss) {
		char[] s = ss.toCharArray();
		int n = s.length;
		if (n <= 1) return n;

		int[][] f = new int[n][n];
		int i, j, len;

		for (i = 0; i < n; i++)
			f[i][i] = 1;

		for (i = 0; i < n - 1; i++)
			f[i][i + 1] = (s[i] == s[i + 1]) ? 2 : 1;

		for (len = 3; len <= n; len++) {
			for (i = 0; i <= n - len; i++) {
				j = i + len - 1;

				// case 1
				f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);

				// case 2
				int expand = s[i] == s[j] ? 2 : 0;
				f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + expand);
 			}
		}

		return f[0][n - 1];
	}
}
