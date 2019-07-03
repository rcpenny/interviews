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
		match(str, letters, 0, map);

		return can_match;
	}

	// 递归的定义: 原始数据str与letters，控制条件index:letters检查字母的起始位置，DFS当前位置的状态:对应模式map
	private void match(String str, char[] letters, int index, Map<Character, String> map) {
		// 递归的出口：str被切完了，index查到letters最后一位，能match
		if (str.length() == 0 && index == letters.length) can_match = true;
		if (can_match || str.length() == 0 || index == letters.length) return;

		char current = letters[index];
	
		// 递归的拆解： 有char的对应模式
		if (map.containsKey(current)) {
			// 进入递归的条件： substring不符合当前对应模式
			String word = map.get(current);
			if (!str.startsWith(word)) return;

			String follow = str.substring(word.length());
			match(follow, letters, index + 1, map);
			return;
		}

		// else
		// 递归的拆解： 无char的对应模式,由1 -> str.end一个个试
		for (int i = 1; i <= str.length(); i++) {
			// 进入递归的条件：当前对应模式不存在下一个substring
			String sub = str.substring(0, i);
			if (map.containsValue(sub)) continue;

			map.put(current, sub);
			String following = str.substring(i);
			match(following, letters, index + 1, map);
			map.remove(current);
		}
	}
}
