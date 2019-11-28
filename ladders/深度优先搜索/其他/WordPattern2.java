import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个pattern和一个字符串str，查找str是否遵循相同的模式。
 * 这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。
 * (如果a对应s，那么b不对应s。例如，给定的模式= "ab"， str = "ss"，返回false
 * 
 * 输入: pattern = "abab" str = "redblueredblue"
	 输出: true  说明: "a"->"red","b"->"blue"
 */

public class WordPattern2 {
	private boolean can_match = false;

  public boolean wordPatternMatch(String pattern, String str) {
		char[] letters = pattern.toCharArray();

		Map<Character, String> map = new HashMap<>();
		match(str, letters, 0, map);

		return can_match;
	}

	// 定义: 元数据str, letters, 控制index对应letters, 状态:对应mapping模式
	private void match(String str, char[] letters, int index, Map<Character, String> map) {
		// 出口：能match, str被切完，index到边界
		if (str.length() == 0 && index == letters.length) can_match = true;
		if (can_match || str.length() == 0 || index == letters.length) return;

		char current = letters[index];
	
		// 拆解： 有current的对应模式
		if (map.containsKey(current)) {
			String word = map.get(current);
			if (!str.startsWith(word)) return;  // 进入递归的条件： substring符合当前对应模式

			String follow = str.substring(word.length());
			match(follow, letters, index + 1, map);
			return;
		}

		// 拆解： 无current的对应模式
		for (int i = 1; i <= str.length(); i++) {
			String sub = str.substring(0, i);
			if (map.containsValue(sub)) continue; // 进入递归的条件：当前对应模式不存在下一个substring

			map.put(current, sub);
			String follow = str.substring(i);
			match(follow, letters, index + 1, map);
			map.remove(current);
		}
	}
}
