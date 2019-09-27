import java.util.ArrayList;
import java.util.Set;

// 给一字串s和单词的字典dict,在字串中增加空格来构建一个句子，并且所有单词都来自字典。
// 返回所有有可能的句子

// 输入："lintcode"，["de","ding","co","code","lint"]
// 输出：["lint code", "lint co de"]
// 解释：插入一个空格是"lint code"，插入两个空格是 "lint co de"

public class WordBreak2 {
  public List<String> wordBreak(String s, Set<String> wordDict) {
    Map<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();
    
    return search(s, wordDict, memo);
  }
  
  private List<String> search(String s, Set<String> dict, Map<String, List<String>> memo) {
    if (memo.containsKey(s)) return memo.get(key);

    // s可以组合出的可能性
    List<String> result = new ArrayList<>();
    if (s.length() == 0) return result;

    // 拆解
    if (dict.contains(s)) result.add(s);

    for (int len = 1; len < s.length(); i++) {
      String prefix = s.substring(0, len);
      if (!dict.contains(prefix)) continue; // 进入递归条件：dict 

      String suffix = s.substring(len);

      List<String> suffix_candidates = search(suffix, dict, memo);

      for (String candi : suffix_candidates)
        result.add(prefix + " " + candi);
    }

    memo.put(s, result);

    return result;
  }
}