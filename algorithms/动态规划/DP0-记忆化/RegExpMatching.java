// 实现支持'.'和'*'的正则表达式匹配。
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
// 需要实现的函数是：bool isMatch(string s, string p)

// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true

public class RegExpMatching {
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) return false;

    boolean[][] visited = new boolean[s.length()][p.length()];
    boolean[][] memo = new boolean[s.length()][p.length()];

    return matchHelper(s, p, 0, 0, visited, memo);
  }

  private boolean matchHelper(String s, String p, int si, int pi, boolean[][] visited, boolean[][] memo) {
    if (pi == p.length()) return si == s.length();

    if (si == s.length()) return isEmpty(p, pi);

    if (visited[si][pi]) return memo[si][pi];

    char sc = s.charAt(si);
    char pc = p.charAt(pi);
    boolean match = false;

    // 当前p串中有* ，就有两种选择，*不去匹配，直接用p串的下一个匹配当前s串字符，或者重复p串的上一个字符匹配
    //pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*'
    if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
      match = matchHelper(s, p, si, pi + 2, visited, memo) ||
        (singleMatch(sc, pc) && matchHelper(s, p, si + 1, pi, visited, memo));
    } else {
      match = singleMatch(sc, pc) && matchHelper(s, p, si + 1, pi + 1, visited, memo);
    }

    visited[si][pi] = true;
    memo[si][pi] = match;

    return match;
  }

  private boolean singleMatch(char a, char b) {
    return a == b || b == '.';
  }

  // pattern like 'a*b*c*'
  private boolean isEmpty(String p, int pi) {
    for (int i = pi; i < p.length(); i += 2) {
      if (i + 1 >= p.length() || p.charAt(i + 1) != '*') return false;
    }
    return true;
  }
}
