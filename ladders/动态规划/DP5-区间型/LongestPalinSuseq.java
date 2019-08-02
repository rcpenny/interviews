// 给一字符串 s, 找出在 s 中的最长回文子序列的长度. 你可以假设 s 的最大长度为 1000.
// 输入： "bbbab" 输出： 4
// 解释： 一个可能的最长回文序列为 "bbbb"

// 1. 确定状态: f[i][j] 是S[i...j]的最长回文
// 2. 转移方程: f[i][j] = max{f[i+1][j], f[i][j-1], f[i+1][j-1] + 2(when s[i]==s[j])}
// 3. 初态边界  f[0][0]...f[N-1][N-1] = 1
//             s[i] == s[i+1] f[i][i+1] = 2
//             s[i] != s[i+1] f[i][i+1] = 1
// 4. 顺序计算: 按照长度j-i从小到大去计算 for循环长度，for循环起点

// 动态规划都可以改成记忆化搜索 递推
public class LongestPalinSuseq {
  public int longestPalindromeSubseq(String ss) {
		char[] s = ss.toCharArray();
		int n = s.length;
		if (n <= 1) return n;

		int[][] f = new int[n][n];
		int i, j, len;
		
		for (i = 0; i < n; i++) // len = 1
			f[i][i] = 1;

		for (i = 0; i < n - 1; i++) // len = 2
			f[i][i + 1] = (s[i] == s[i + 1]) ? 2 : 1;

		for (len = 3; len <= n; len++) {
			for (i = 0; i <= n - len; i++) {
				j = i + len - 1;
				// case 1
				f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);

				// case 2
				if (s[i] == s[j])
					f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
 			}
		}

		return f[0][n - 1];
	}
}
