// https://www.lintcode.com/problem/scramble-string/

// 1. 确定状态: f[i][j][k][h] 表示 t[k...h] 是否由 s[i...j]
//             j-i == h-k
//             f[i][j][k] 为 s从i开始， t从j开始，长度为k的子串
// 2. 转移方程: 
// 3. 初态边界  
// 4. 顺序计算

public class ScrambleString {
  public boolean isScramble(String s, String t) {
		int m = s.length();
		int n = t.length();

		if (m != n) return false;

		boolean[][][] f = new int[n][n][n + 1];
		int i, j, w, len;
		// len = 1
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				f[i][j][1] = (s.charAt(i) == t.charAt(j));
		
		for (len = 2; len <= n; len++) {
			for (i = 0; i <= n - len; i++) {
				for (j = 0; j <= n - len; j++) {
					for (w = 1; w < len; w++) {
						// no swap 
						// s1->t1, s2->t2
						if (f[i][j][w] && f[i + w][j + w][len - w]) {
							f[i][j][len] =  true;
							break;
						}

						// swap
						if (f[i][j + len - w][w] && f[i + w][j][len - w]) {
							f[i][j][len] = true;
							break;
						}
					}
				}
			}
		}

		return f[0][0][n];
	}
}
