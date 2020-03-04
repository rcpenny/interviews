import java.util.ArrayList;
import java.util.List;

// 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
// 返回所有可能的分割方案.

public class PalindromePartition {
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		if (s == null || s.length() == 0) return result;

		search(s, s.toCharArray(), 0, new ArrayList<>(), result);

		return result;
	}

	private void search(String s, char[] c, int start, List<String> combs, List<List<String>> result) {
		if (start == s.length()) {
			result.add(new ArrayList<>(combs));
			return;
		}

		for (int i = start; i < s.length(); i++) {
			if (!isPalindrome(c, start, i)) continue;
			String sub = s.substring(start, i + 1);
			combs.add(sub);
			search(s, c, i + 1, combs, result);
			combs.remove(combs.size() - 1);
		}
	}

	private boolean isPalindrome(char[] c, int start, int end) {
		while (start < end)
			if (c[start++] != c[end--]) return false;
		return true;
	}
}
