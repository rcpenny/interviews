
public class LongestPalindromicSubstring {

  @TwoPointer
  public String longestPalindrome(String s) {
    if (s == null) return s;
    if (s.length() == 0) return s;
    
    char[] c = s.toCharArray();
    
    int left = 0, right = 0;
    int max = 1;
    
    int start = 0, end = 0;
    
    for (int i = 0; i < c.length; i++) {
      int len = 1;
      
      // odd length case
      left = i - 1;
      right = i + 1;
      while (left >= 0 && right < c.length) {
        if (c[left] != c[right]) break;
        len += 2;
        if (len > max) {
          max = len;
          start = left;
          end = right;
        }
        left--;
        right++;
      }
      
      // even length case
      left = i;
      right = i + 1;
      len = 0;
      while (left >= 0 && right < c.length) {
        if (c[left] != c[right]) break;
        len += 2;
        if (len > max) {
          max = len;
          start = left;
          end = right;
        }
        left--;
        right++;
      }      
    }

    return s.substring(start, end + 1);
  }
}