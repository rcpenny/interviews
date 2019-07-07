// 给定两个字符串 source 和 target. 求 source 中最短的包含 target 中每一个字符的子串.

// 输入: source = "adobecodebanc", target = "abc"
// 输出: "banc"
// 解释: "banc" 是 source 的包含 target 的每一个字符的最短的子串.
// Challenge
// O(n) 时间复杂度

// Notice
// 如果没有答案, 返回 "".
// 保证答案是唯一的.
// target 可能包含重复的字符, 而你的答案需要包含至少相同数量的该字符.

public class MinimumWindowSubstring {
  int[] letters = new int[256];

  public String minWindow(String source , String target) {
    for (int i = 0; i < target.length(); i++) {
      letters[target.charAt(i)]++;
    }
    
    int slow = 0, fast = 0, min_length = Integer.MAX_VALUE;
    String min_window = "";
      
    for (slow = 0; slow < source.length(); slow++) {
      // fast未到头，且还没包含target
      while (fast < source.length() && !isAllFound()) {
        letters[source.charAt(fast)]--;
        fast++;
      }
      
      if (fast - slow < min_length && isAllFound()) {
          min_length = fast - slow;
          min_window = source.substring(slow, fast);
      }
      letters[source.charAt(slow)]++;
    }

    return min_window;     
  }
  
  private boolean isAllFound() {
    for (int i = 0; i < letters.length; i++)
      if (letters[i] > 0) return false;
    return true;
  }
}