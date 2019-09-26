// 给出两个字符串，找到最长公共子串，并返回其长度。

// 样例 1:
// 	输入:  "ABCD" and "CBCE"
// 	输出:  2
	
// 	解释:
// 	最长公共子串是 "BC"

// 转移方程有点难想
public class LongestCommonSubstring {
	public int longestCommonSubstring(String A, String B) {
		int m = A.length();
		int n = B.length();
		
		// f[i][j] 表示A的前i个字符与B的前j个字符中，且以第i个和第j个为结尾的公共子串的长度。
		int[][] f = new int[m + 1][n + 1];
		
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				f[i][j] = 0;
				if (a[i - 1] == b[j - 1])
					f[i][j] = f[i - 1][j - 1] + 1; // "ABCD" "CBCE" f[3][3] = f[2][2] + 1
			}
		}
		
		int max = 0;
		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= n; j++)
				max = Math.max(max, f[i][j]);
		
		return max;
	}
}
