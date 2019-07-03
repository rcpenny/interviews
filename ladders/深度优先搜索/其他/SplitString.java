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
		if (s == null) return results;

		split(s, 0, new ArrayList<String>(), results);

		return results;
	}

	// 递归的定义：原始数据str, 控制变量start index, dfs当前节点的状态string subs
	private void split(String s, int start, List<String> subs,
		List<List<String>> results) {
		// 递归的出口: subs不管作用，控制变量start index到头了
		if (start == s.length()) results.add(new ArrayList<String>(subs));
		if (start >= s.length()) return;

		// 递归的拆解： 切1,或者切2
		for (int len = 1; len <= 2; len++) {
			// 进入递归的条件：index不越界
			if (start + len > s.length()) continue;
	
			subs.add(s.substring(start, start + len));
			split(s, start + len, subs, results);
			subs.remove(subs.size() - 1);
		}
	}
}
