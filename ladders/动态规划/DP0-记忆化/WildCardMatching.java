// 判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：
// '?' 可以匹配任何单个字符。
// '*' 可以匹配任意字符串（包括空字符串）。
// 两个串完全匹配才算匹配成功。

// 输入:"ab"  "?*"  输出: true
// 说明: '?' -> 'a' '*' -> 'b'

// 使用深度优先搜索 + 记忆化的版本。
// 用一个二维的 boolean 数组来当记忆化数组，记录 s 从 sIndex 开始的后缀 能够匹配上 p 从 pIndex 开始的后缀

public class WildcardMatching {
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) return false;

    boolean[][] visited = new boolean[s.length()][p.length()];
    boolean[][] memo = new boolean[s.length()][p.length()];

    return matchHelper(s, 0, p, 0, visited, memo);
  }

  // 定义： 元数据 s, p,   控制 si, pi   状态 visited, memo
  private boolean matchHelper(String s, int si, String p, int pi, boolean[][] visited, boolean[][] memo) {
    // 出口: pi到头了，si也必须到头才匹配
    if (pi == p.length()) return si == s.length();

    // 出口：si到头了，pi之后必须是 * 来匹配
    if (si == s.length()) return allstar(p, pi);

    // 出口：如果这个匹配位置已经测过，返回测过的值
    if (visited[si][pi]) return memo[si][pi];

    char sc = s.charAt(si);
    char pc = p.charAt(pi);
    boolean match;

    // 是*时，                          *不动，s向右一位                      *为空，p向右一位
    if (pc == '*') match = matchHelper(s, si + 1, p, pi, visited, memo) || matchHelper(s, si, p, pi + 1, visited, memo);
    else match = singleMatch(sc, pc) && matchHelper(s, si + 1, p, pi + 1, visited, memo);

    visited[si][pi] = true;
    memo[si][pi] = match;
    return match;
  }

  private boolean singleMatch(char a, char b) {
    return (a == b || b == '?');
  }

  private boolean allstar(String p, int pi) {
    for (int i = pi; i < p.length(); i++)
      if (p.charAt(i) != '*') return false;
    return true;
  }
}
