// 给你两个字符串 S 和 T, 判断他们是否只差一步编辑。
// 输入: s = "aDb", t = "adb" 
// 输出: true

public class OneEditDistance {
  public boolean isOneEditDistance(String s, String t) {
		int m = s.length();
		int n = t.length();

		if (Math.abs(m - n) > 1) return false;

		if (m > n) return isOneEditDistance(t, s);

		// t length > s length
		for (int i = 0; i < m; i++) {
			if (s.charAt(i) == t.charAt(i)) continue;

			if (m == n) return s.substring(i + 1).equals(t.substring(i + 1));
			return s.substring(i).equals(t.substring(i + 1));
		}

		return m != n;
	}
}
