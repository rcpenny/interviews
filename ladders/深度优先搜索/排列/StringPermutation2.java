import java.util.ArrayList;
import java.util.Arrays;

//  Given a string, find all permutations of it without duplicates.
// Input: "aabb" Output: ["aabb", "abab", "baba", "bbaa", "abba", "baab"]
// 和重复数字选择一样

public class StringPermutation {

  public List<String> stringPermutation2(String str) {
		List<String> results = new ArrayList<>();
		if (str == null) return results;

		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		boolean[] visited = new boolean[str.length()];

		dfs(chars, visited, new StringBuilder(), results);

		return results;
	}

	// 定义： 元数据chars, 控制visited, 状态sb, 结果results
	private void dfs(char[] chars, boolean[] visited, StringBuilder sb, List<String> result) {
		// 出口：状态长度 = 元数据长度
		if (sb.length() == chars.length) {
			result.add(sb.toString());
			return;
		}

		// 拆解：从头开始扫，哪些字母可以选
		for (int i = 0; i < chars.length; i++) {
			if (visited[i]) continue; // 进入递归的条件：这个位置的char是否被选过 && 该层是否以这个char开头过了
			if (i != 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;

			sb.append(chars[i]);
			visited[i] = true;
			dfs(chars, visited, sb, result);
			sb.deleteCharAt(sb.length() - 1);
			visited[i] = false;
		}
	}
}
