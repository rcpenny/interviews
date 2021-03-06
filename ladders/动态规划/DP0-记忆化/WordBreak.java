import java.util.Set;

// 给定字符串 s 和单词字典 dict，确定 s 是否可以分成一个或多个以空格分隔的子串，并且这些子串都在字典中存在。
// 样例 1: 输入:  "lintcode", ["lint", "code"] 输出:  true

// 1. 确定状态 memo[i] 前i个字符是否可以拼成words
// 2. 转移方程 memo[i] = TRUE if exists memo[j] == TRUE and s(j...i) IN DICT (j < i)
// 3. 初态边界 memo[0] = TRUE, 这样就能看所有从头到i位置的substring是否是word
// 4. 顺序计算 memo[0] -> memo[i + 1]

// 序列型(划分型)动态规划，前i个的可行性

public class WordBreak {
  public boolean wordBreak(String s, Set<String> dict) {
		if (s == null) return false;

		int n = s.length();
		boolean[] f = new boolean[n + 1];
		f[0] = true;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				String sub = s.substring(j, i);
				if (f[j] && dict.contains(sub)) {
					f[i] = true;
					break;
				}
			}
		}

		return f[n];
  }
}
