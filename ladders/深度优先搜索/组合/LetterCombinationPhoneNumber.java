import java.util.ArrayList;

/**
 * 给一个不包含'0'和'1'的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
 * 下图的手机按键图，就表示了每个数字可以代表的字母
 * 
 * 输入: "23"
 * 输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * 解释: 
 * '2' 可以是 'a', 'b' 或 'c'
 * '3' 可以是 'd', 'e' 或 'f'
 */
public class LetterCombinationPhoneNumber {
	private static final String[] letters = 
		{"abc", "def", "ghi", "jkl", "mno", "pqrs","tuv", "wxyz"};

  public List<String> letterCombinations(String digits) {
		List<String> results = new ArrayList<>();
		if (digits == null || digits.length() == 0) return results;

		dfs(digits, 0, new StringBuilder(), results);
		
		return results;
	}

	private void dfs(String digits, int index, StringBuilder sb, List<String> results) {
		if (sb.length() == digits.length()) {
			results.add(sb.toString());
			return;
		}

		for (int i = index; i < digits.length(); i++) {
			int digit = digits.charAt(i) - '0';
			for (char c : letters[digit - 2].toCharArray()) {
				sb.append(c);
				dfs(digits, i + 1, sb, results);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
