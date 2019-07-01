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

	private void split(String s, int start, List<String> subs,
		List<List<String>> results) {
		if (start == s.length()) {
			results.add(new ArrayList<String>(subs));
			return;
		}

		if (start + 1 <= s.length()) {
			subs.add(s.substring(start, start + 1));
			split(s, start + 1, subs, results);
			subs.remove(subs.size() - 1);
		}

		if (start + 2 <= s.length()) {
			subs.add(s.substring(start, start + 2));
			split(s, start + 2, subs, results);
			subs.remove(subs.size() - 1);
		}
	}
}
