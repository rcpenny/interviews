/**
 * 给一个非空字符串 s，你最多可以删除一个字符。
 * 判断是否可以把它变成回文串。
 * 
 * 输入: s = "abca"
 * 输出: true
 * 解释: 删除 'b' 或 'c'
 * 
 * 给定的字符串只包含小写字母
 * 字符串的长度最大为 50000
 * 
 * 指针算法。从两头走到中间，发现第一对不一样的字符之后，要么删左边的，要么删右边的。
 */
public class ValidPalidrome2 {

  public boolean validPalindrome(String s) {
		if (s == null || s.length() == 0) return true;

		int start = 0, end = s.length() - 1;
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return isSubStringPalidrome(s, start + 1, end) || isSubStringPalidrome(s, start, end - 1);
			}
			start++;
			end--;
		}
		return true;
	}

	private boolean isSubStringPalidrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) return false;
			left++;
			right--;
		}
		return true;
	}
}
