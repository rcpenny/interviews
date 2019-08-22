import java.util.HashMap;
import java.util.Map;

// lint638

// 输入 : s = "egg", t = "add"
// 输出 : true 
// 说明 : e -> a, g -> d

public class IsomorphicStrings {

  public boolean isIsomorphic(String s, String t) {
    Map<Character, Character> s2t = new HashMap<>();
    Map<Character, Character> t2s = new HashMap<>();

    if (s == null || t == null) return false;
    if (s.length() != t.length()) return false;

    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();

    for (int i = 0; i < sc.length; i++) {
      char scc = sc[i];
      char tcc = tc[i];

      // 两个映射里都没有
      if (!s2t.containsKey(scc) && !t2s.containsKey(tcc)) {
        s2t.put(scc, tcc);
        t2s.put(tcc, scc);
        continue;
      }
      
      // 只有一个映射有，return false
      if (!s2t.containsKey(scc) || !t2s.containsKey(tcc))
        return false;

      // 都有，但不相同
      if (s2t.get(scc) != tcc || t2s.get(tcc) != scc) 
        return false;
    }

    return true;
  }
}