// 给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。

// 你总共三种操作方法：
// 插入一个字符
// 删除一个字符
// 替换一个字符

// 全部操作完成后word1长度也会变成n 且 word1[n-1] = word2[n-1]

// 1. 确定状态: f[i][j] 为A前i个字符A[0...i-1]和B前j个字符B[0...j-1]的最小编辑距离

// 2. 转移方程: f[i][j] = MIN {
//               f[i][j-1] + 1                  A在最后插入B[j-1]         INSERT
//               f[i-1][j] + 1                  A删掉最后一个字符          DELETE
//               f[i-1][j-1] + 1                A最后一个字符换成B[j-1]    REPLACE
//               f[i-1][j-1] | A[i-1] = B[j-1]  AB最后字符相同            SAME
//             }
//             将A转化成B

// 3. 初态边界:  f[0][j] = j
//             f[i][0] = i

public class EditDistance {
	public int minDistance(String word1, String word2) {
		char[] A = word1.toCharArray();
		char[] B = word2.toCharArray();

		int m = A.length;
		int n = B.length;

		int[][] f = new int[m + 1][n + 1];
		int i, j;

		// i可以大于j
		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (i == 0) {  // 前i个字符A，是空串
					f[i][j] = j; // insert j 次
					continue;
				}

				if (j == 0) { // 前j个字符B，是空串
					f[i][j] = i; // delete i 次
					continue;
				}

				// 这时前三个决策或许会与第四个决策一样, 但是绝对不会比第四个决策更优. 与 LCS 的情况类似, 同样是贪心.
				if (A[i - 1] == B[j - 1]) {
					f[i][j] = f[i - 1][j - 1];
					continue;
				}

				f[i][j] = Math.min(f[i-1][j-1], Math.min(f[i][j-1], f[i-1][j])) + 1;
			}
		}

		return f[m][n];
  }
}
