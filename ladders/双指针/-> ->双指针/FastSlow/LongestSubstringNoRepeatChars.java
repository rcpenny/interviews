// 给定一个字符串，请找出其中无重复字符的最长子字符串

public class LongestSubstringNoRepeatChars {
	public int lengthOfLongestSubstring(String s) {
		int longest = 0;
		if (s == null || s.length() == 0) return longest;

		int fast = 0;
		int slow = 0;
		int[] letters = new int[256];
		char[] array = s.toCharArray();

		for (slow = 0; slow < array.length; slow++) {
			
			// fast一路前移到出现重复
			while (fast < array.length) {
				char head = array[fast];
        if (letters[head] != 0) break;
        letters[head] = 1;
        longest = Math.max(longest, fast - slow + 1);
        fast++;
			}

			char tail = array[slow];
			letters[tail] = 0;
		}

		return longest;
	}
}
