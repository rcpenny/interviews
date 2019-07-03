import java.util.HashSet;
import java.util.Set;

/**
 * Giving a string with number from 1-n in random order, but miss 1 number.Find that number.
    Example
    Example1

    Input: n = 20 and str = 19201234567891011121314151618
    Output: 17
    Explanation:
    19'20'1'2'3'4'5'6'7'8'9'10'11'12'13'14'15'16'18
 */

public class findMissingNumber {
	public int missing = -1;

  public int findMissing2(int n, String str) {
		if (n <= 0) return missing;

		boolean[] found = new boolean[n + 1];
		find(str, 0, 0, n, found);

		return missing;
	}
	
	private void find(String str, int index, int count, int n, boolean[] found) {
		// recursion exit 限制条件 count或index达到某个值
		if ( count == n - 1 && index == str.length()) {
			for (int i = 1; i < found.length; i++) if (!found[i]) missing = i;
		}
		if (count >= n - 1 || index >= str.length()) return;

		for (int len = 1; len <= 2; len++) {
			// 进入下个递归前的额外操作：去重？去掉特殊条件？
			if (index + len > str.length()) continue;
			String sub = str.substring(index, index + len);
			if (!isValidNumber(sub, n, found)) continue;
			
			int val = Integer.valueOf(sub);
			found[val] = true;
			find(str, index + len, count + 1, n, found);
			found[val] = false;
		}
	}

	private boolean isValidNumber(String sub, int n, boolean[] found) {
		// 去掉 “06”这样的case
		if (sub.charAt(0) == '0') return false;
		int val = Integer.valueOf(sub);
		return (val > 0 && val <= n && !found[val]) ? true : false;
	}
}
