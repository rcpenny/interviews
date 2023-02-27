// 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。

// 交叉字符串

// 输入:
// "aabcc"
// "dbbca"
// "aadbbcbcac"
// 输出:
// true

// 1. 确定状态: f[i][j] s3的前i+j个字符是否由s1的前i个字符和s2的前j个字符组成
// 2. 转移方程: f[i][j] = 
//             f[i-1][j] AND S3[i+j-1] = S1[i-1] 或
//             f[i][j-1] AND S3[i+j-1] = S2[j-1]
// 3. 初态边界  f[0][0] = true, i=0不看情况1, j=0不看情况2
// 4. 顺序计算  从小到大

public class InterleavingString {
  public boolean isInterleave(String s1, String s2, String s3) {
		char[] A = s1.toCharArray();
		char[] B = s2.toCharArray();
		char[] X = s3.toCharArray();

		int m = A.length;
		int n = B.length;
		if (X.length != m + n) return false;

		boolean[][] f = new boolean[2][n + 1];
		int i, j;

		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (i == 0 && j == 0) {
					f[i][j] = true;
					continue;
				}

				f[i][j] = false;

				if (i > 0 && X[i + j - 1] == A[i - 1] && f[i - 1][j])
					f[i][j] = true;

				if (j > 0 && X[i + j - 1] == B[j - 1] && f[i][j - 1])
					f[i][j] = true;
			}
		}

		return f[m][n];
	}
}
