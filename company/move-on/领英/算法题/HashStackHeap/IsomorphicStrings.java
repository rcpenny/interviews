
// 输入 : s = "egg", t = "add"
// 输出 : true 
// 说明 : e -> a, g -> d

// leet205
public class IsomorphicStrings {
  public boolean isIsomorphic(String s, String t) {
		Map<Character, Character> s2t = new HashMap<>();
		Map<Character, Character> t2s = new HashMap<>();

		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();

		int n = sc.length;

		for (int i = 0; i < n; i++) {
			char scc = sc[i];
			char tcc = tc[i];

			// 都无
			if (!s2t.containsKey(scc) && !t2s.containsKey(tcc)) {
				s2t.put(scc, tcc);
				t2s.put(tcc, scc);
				continue;
			}

			// 都有
			if (s2t.containsKey(scc) && t2s.containsKey(tcc)) {
				if (s2t.get(scc) != tcc || t2s.get(tcc) != scc) {
					return false;
				}
				continue;
			}

			// 一个有一个没有，已不符合同构
			return false;
		}

		return true;
 }
}
