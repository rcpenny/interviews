// 实现支持'.'和'*'的正则表达式匹配。
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
// 需要实现的函数是：bool isMatch(string s, string p)

// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true

// 1. 确定状态: f[i][j] A的前i个字符和B的前j个字符是否匹配
// 2. 转移方程: f[i][j] = 
//            f[i-1][j-1] 如果 i > 0 且B[j-1]='.'或者A[i-1] = B[j-1]
//            f[i][j-2] OR （f[i-1][j] AND B[j-2]='.' OR B[j-2]=A[i-1])) 如果B[j-1]='*'
// 3. 初态边界  f[0][0] = TRUE   f[i][0] = FALSE

public class RegularExpMatching {

  public boolean isMatch(String A, String B) {
		char[] s = A.toCharArray();
		char[] p = B.toCharArray();

		int m = s.length;
		int n = p.length;

		boolean[][] f = new boolean[m + 1][n + 1];
		int i, j;

		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (i == 0 && j == 0) {
					f[i][j] = true;
					continue;
				}

				if (j == 0) {
					f[i][j] = false;
					continue;
				}

				f[i][j] = false;
				if (p[j - 1] != '*') {
					if (i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
						f[i][j] = f[i - 1][j - 1];
					}
				} else {
					// c*
					// 0 c's
					if (j > 1) {
						f[i][j] = f[i][j - 2];
					}

					// >=1 c's c: p[j-2]
					if (i > 0 && j > 1 && p[j - 2] == '.' || p[j - 2] == s[i - 1]) {
						f[i][j] = f[i][j] || f[i - 1][j];
					}
				}
			}
		}

		return f[m][n];
  }
}
