import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。
// 如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。

// 输入:["lint", "intl", "inlt", "code"]
// 输出:["lint", "inlt", "intl"]

public class Anagrams {
	public List<String> anagrams(String[] strs) {
		Map<String, List<String>> patToAna = new HashMap<>();

		for (String s : strs) {
			char[] c = s.toCharArray();
			Arrays.sort(c);
			String tmp = String.valueOf(c);

			patToAna.putIfAbsent(tmp, new ArrayList<>());
			patToAna.get(tmp).add(s);
		}

		List<String> result = new ArrayList<>();

		for (String entry : patToAna.keySet())
			if (patToAna.get(entry).size() > 1)
				result.addAll(patToAna.get(entry));

		return result;
	}
}
