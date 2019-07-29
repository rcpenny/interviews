// 给定字符串 S 和 T, 计算 S 的所有子序列中有多少个 T.
// 子序列字符串是原始字符串删除一些(或零个)字符之后得到的字符串, 
// 并且要求剩下的字符的相对位置不能改变. (比如 "ACE" 是 ABCDE 的一个子序列, 而 "AEC" 不是)

// 输入: S = "rabbbit", T = "rabbit"
// 输出: 3
// 解释: 你可以删除 S 中的任意一个 'b', 所以一共有 3 种方式得到 T.

// 输入: S = "abcd", T = ""
// 输出: 1
// 解释: 只有删除 S 中的所有字符这一种方式得到 T

// 1. 确定状态: f[i][j] 为S前i个字符A[0...i-1]得到T前j个字符T[0...j-1]的方式
// 2. 转移方程: f[i][j] = f[i-1][j] + (f[i-1][j-1] | S[i-1] = T[j-1]  ST最后字符相同)
// 3. 初态边界: f[i][0] = 1   T为空，只有删除S中所有字符这一种方式   f[0][j] = 0 (除j=0时)
// 4. 顺序计算: 00 -> 0n -> mn

public class DistinctSubseq {
	public int numDistinct(String S, String T) {
		if (S == null || T == null) return 0;

		int m = S.length();
		int n = T.length();

		int i, j;

		int[][] f = new int[m + 1][n + 1];
		for (i = 0; i <= m; i++) f[i][0] = 1;

		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				f[i][j] = f[i - 1][j];
				if (S.charAt(i - 1) == T.charAt(j - 1))
					f[i][j] += f[i - 1][j - 1];
			}
		}

		return f[m][n];
	}
}
