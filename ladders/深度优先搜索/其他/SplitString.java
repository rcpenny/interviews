import java.util.ArrayList;
import java.util.List;

/**
 * Give a string, you can choose to split the string after one character or two adjacent characters, 
 * and make the string to be composed of only one character or two characters. Output all possible results.
 * 
 * Input: "123"
 * Output: [["1","2","3"],["12","3"],["1","23"]]
 */
public class SplitString {

  public List<List<String>> splitString(String s) {
		List<List<String>> results = new ArrayList<>();
		if (s == null || s.length() == 0) return results;

		split(s, 0, new ArrayList<String>(), results);

		return results;
	}

	// 定义：元数据str, 控制index, 状态subs, 结果 results
	private void split(String s, int start, List<String> subs, List<List<String>> results) {
		// 出口: index到达极限
		if (start == s.length()) results.add(new ArrayList<String>(subs));
		if (start >= s.length()) return;

		// 拆解： 切1,切2
		for (int len = 1; len <= 2; len++) {
			if (start + len > s.length()) continue; // 进入递归的条件：index不越界
	
			subs.add(s.substring(start, start + len));
			split(s, start + len, subs, results);
			subs.remove(subs.size() - 1);
		}
	}
}
