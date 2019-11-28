// 给定两个字符串s1和s2，如果s2包含s1的排列，则写一个函数返回true。 
// 换句话说，第一个字符串的排列之一是第二个字符串的substring。

// leet567

// 输入: s1 = "ab" s2 = "eidbaooo"
// 输出: true
// 解释: s2包含s1的一个排列("ba").

// 输入: s1= "ab" s2 = "eidboaoo"
// 输出: false

class PermutationInString {
  public boolean checkInclusion(String s1, String s2) {
    if (s1 == null) return false;
    if (s2.length() <= s1.length()) return false;

    int[] letters = new int[26];

    for (char c : s1.toCharArray())
      letters[c - 'a']++;
    
    int n = s1.length();
    int[] current = new int[26];
    char[] c2 = s2.toCharArray();

    for (int i = 0; i < n; i++) {
      char c = c2[i];
      current[c - 'a']++;
    }
    
    if (isPermutation(letters, current)) return true;

    for (int i = 0; i < s2.length() - n; i++) {
      char left = c2[i];
      char right = c2[i + n];

      current[left - 'a']--;
      current[right - 'a']++;

      if (isPermutation(letters, current)) return true;
    }

    return false;
  }

  private boolean isPermutation(int[] letters, int[] current) {
    for (int i = 0; i < 26; i++)
      if (letters[i] != current[i]) return false;
    
    return true;
  }
}