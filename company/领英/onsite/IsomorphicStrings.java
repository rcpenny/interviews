import java.util.HashMap;
import java.util.Map;

// lint638

// 输入 : s = "egg", t = "add"
// 输出 : true 
// 说明 : e -> a, g -> d

public class IsomorphicStrings {

  public boolean isIsomorphic(String ss, String tt) {
    Map<Character, Character> s2t = new HashMap<>();
    Map<Character, Character> t2s = new HashMap<>();

    if (ss == null || tt == null) return false;
    if (ss.length() != tt.length()) return false;

    char[] s = ss.toCharArray();
    char[] t = tt.toCharArray();

    for (int i = 0; i < s.length; i++) {
      char sc = s[i];
      char tc = t[i];

      // 两个映射里都没有
      if (!s2t.containsKey(sc) && !t2s.containsKey(tc)) {
        s2t.put(sc, tc);
        t2s.put(tc, sc);
        continue;
      }
      
      // 只有一个映射有，return false
      if (!s2t.containsKey(sc) || !t2s.containsKey(tc))
        return false;

      // 都有，但不相同
      if (s2t.get(sc) != tc || t2s.get(tc) != sc) 
        return false;
    }

    return true;
  }
}
