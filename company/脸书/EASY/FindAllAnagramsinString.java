// 输入 : s = "cbaebabacd", p = "abc"
// 输出 : [0, 6]
// 说明 : 
// 子串起始索引 index = 0 是 "cba"，是"abc"的字谜。
// 子串起始索引 index = 6 是 "bac"，是"abc"的字谜。

public class FindAllAnagramsinString {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    
    if (s.length() < p.length()) return result;
    
    int[] letters = new int[256];
    
    for (char tmp : p.toCharArray())
      letters[tmp]++;
      
    int n = p.length();
    
    int[] current = new int[256];
    
    for (int i = 0; i < n; i++) {
      current[s.charAt(i)]++;
    }
    
    if (isAnagram(letters, current)) result.add(0);
    
    
    char[] cs = s.toCharArray();
    for (int i = 0; i < s.length() - n; i++) {
      current[cs[i]]--;
      current[cs[i + n]]++;
      if (isAnagram(letters, current)) result.add(i + 1);
    }
      
    return result;
  }
  
  private boolean isAnagram(int[] letters, int[] current) {
    for (int i = 0; i < 256; i++)
      if (letters[i] != current[i]) return false;
    return true;
  }
}