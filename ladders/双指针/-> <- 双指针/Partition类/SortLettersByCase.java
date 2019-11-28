/**
 * Given a string which contains only letters. Sort it by lower case first and upper case second.
 */
public class SortLettersByCase {
  public void sortLetters(char[] chars) {
		if (chars == null || chars.length == 0) return;
		int left = 0, right = chars.length - 1;

		while (left <= right) {
			while (left <= right && Character.isLowerCase(chars[left])) left++;
			while (left <= right && Character.isUpperCase(chars[right])) right--;
			if (left <= right) {
				char tmp = chars[left];
				chars[left] = chars[right];
				chars[right] = tmp;
				left++;
				right--;
			}
		}
	}
}
