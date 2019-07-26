// 给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
// 返回所有有可能的句子

// 输入："lintcode"，["de","ding","co","code","lint"]
// 输出：["lint code", "lint co de"]
// 解释：插入一个空格是"lint code"，插入两个空格是 "lint co de"

// 1. 确定状态 memo[i][j] 前i-j个字符是否可以拼成words
// 2. 转移方程 memo[i][j] = TRUE if exists memo[j] == TRUE and s(j...i) IN DICT (j < i)
// 3. 初态边界 memo[0] = TRUE, 这样就能看所有从头到i位置的substring是否是word
// 4. 顺序计算 memo[0] -> memo[i + 1]

public class WordBreak2 {
  public List<String> wordBreak(String s, Set<String> wordDict) {
		
	}
}
