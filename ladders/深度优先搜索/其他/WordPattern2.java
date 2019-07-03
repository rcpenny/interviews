import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个pattern和一个字符串str，查找str是否遵循相同的模式。
 * 这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。
 * (如果a对应s，那么b不对应s。例如，给定的模式= "ab"， str = "ss"，返回false
 * 
 * 输入:
		pattern = "abab"
		str = "redblueredblue"
		输出: true
		说明: "a"->"red","b"->"blue"
 */
final class WordPattern2 {
	private boolean can_match = false;

  public boolean wordPatternMatch(String pattern, String str) {
		char[] letters = pattern.toCharArray();
		Map<Character, String> map = new HashMap<>();

		match(letters, 0, map, str);
		return can_match;
	}

	// index is the current char in pattern that is being visisted
	private void match(char[] letters, int index, Map<Character, String> map, String str) {
		// recursion exit
		if (str.length() == 0 && index == letters.length) can_match = true;
		if (can_match || str.length() == 0 || index == letters.length) return;

		char current = letters[index];
	
		if (map.containsKey(current)) {
			String word = map.get(current);
			if (!str.startsWith(word)) return;
			match(letters, index + 1, map, str.substring(word.length()));
			return;
		}

		// when char not in map
		for (int i = 1; i <= str.length(); i++) {
			String sub = str.substring(0, i);
			if (map.containsValue(sub)) continue;
			map.put(current, sub);
			match(letters, index + 1, map, str.substring(i));
			map.remove(current);
		}
	}
}
