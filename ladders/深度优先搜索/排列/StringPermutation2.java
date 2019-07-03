import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a string, find all permutations of it without duplicates.
		Input: "abb"
		Output:
		["abb", "bab", "bba"]

		Input: "aabb"
		Output:
		["aabb", "abab", "baba", "bbaa", "abba", "baab"]
 */

public class StringPermutation {
  public List<String> stringPermutation2(String str) {
		List<String> results = new ArrayList<>();
		if (str == null) return results;

		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		boolean[] visisted = new boolean[str.length()];
		dfs(chars, visisted, new StringBuilder(), results);

		return results;
	}

	private void dfs(char[] chars, boolean[] visisted, StringBuilder sb, 
		List<String> result) {
		if (sb.length() == chars.length) {
			result.add(sb.toString());
			return;
		}

		for (int i = 0; i < chars.length; i++) {
			if (visisted[i]) continue;
			if (i != 0 && chars[i] == chars[i - 1] && !visisted[i - 1]) continue;

			sb.append(chars[i]);
			visisted[i] = true;
			dfs(chars, visisted, sb, result);
			sb.deleteCharAt(sb.length() - 1);
			visisted[i] = false;
		}
	}
}
