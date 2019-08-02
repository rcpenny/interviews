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

      if (!s2t.containsKey(scc) && !t2s.containsKey(tcc)) {
        s2t.put(scc, tcc);
        t2s.put(tcc, scc);
        continue;
      }
      
      if (!s2t.containsKey(scc) || !t2s.containsKey(tcc)) {
        return false;
      }

      if (s2t.get(scc) != tcc || t2s.get(tcc) != scc) return false;
    }

    return true;
  }
}