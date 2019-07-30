import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
// 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
// （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
// 返回 words 中与给定模式匹配的单词列表。
// 你可以按任何顺序返回答案。

// 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
// 输出：["aqq","mee"]
// 解释：
// "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
// "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
// 因为 a 和 b 映射到同一个字母。

public class FindAndReplacePattern {
  public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> results = new ArrayList<>();
		
		// bi-jection 所以建立两个hashmap
		// 其实建立一个也可以，根据一个map生成pattern对应的string, 再和word比较就是了
		Map<Character, Character> w2p = new HashMap<>();
		Map<Character, Character> p2w = new HashMap<>();

		for (String word : words) {
			if (word.length() != pattern.length()) continue;

			w2p.clear();
			p2w.clear();

			boolean match = true;
			for (int i = 0; i < word.length(); i++) {
				char w = word.charAt(i);
				char p = pattern.charAt(i);

				if (!w2p.containsKey(w) && !p2w.containsKey(p)) {
					w2p.put(w, p);
					p2w.put(p, w);
					continue;
				}

				if (w2p.containsKey(w) && w2p.get(w) == p 
					&& p2w.containsKey(p) && p2w.get(p) == w) {
					continue;
				}

				match = false;
				break;
			}
			if (match) results.add(word);
		}

		return results;
	}
}
