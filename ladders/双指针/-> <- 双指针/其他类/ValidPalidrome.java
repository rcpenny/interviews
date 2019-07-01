/**
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.
 * 
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama"
 */
public class ValidPalidrome {
  public boolean isPalindrome(String s) {
		if (s == null || s.length() == 0) return true;
		int start = 0, end = s.length() - 1;

		while (start < end) {
			if (!valid(s.charAt(start))) {
				start++;
				continue;
			}
			if (!valid(s.charAt(end))) {
				end--;
				continue;
			}

			char left = Character.toLowerCase(s.charAt(start));
			char right = Character.toLowerCase(s.charAt(end));
			if (left != right) return false;
			// 注意！
			start++;
			end--;
		}
		return true;
	}

	private boolean valid(char c) {
		return Character.isLetter(c) || Character.isDigit(c);
	}
}
