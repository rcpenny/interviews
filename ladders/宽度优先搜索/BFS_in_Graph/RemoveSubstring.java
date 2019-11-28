import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给出一个字符串 s 以及 n 个子串。你可以从字符串 s 中移除 n 个子串中的任意一个，使得剩下来s的长度最小，输出这个最小长度。
		输入:"ccdaabcdbb" ["ab","cd"]
		输出: 2
		解释: ccdaabcdbb -> ccdacdbb -> cabb -> cb (length = 2)
 */

public class RemoveSubstring {

  public int minLength(String s, Set<String> dict) {
		Queue<String> queue = new LinkedList<>();
		Set<String> seen = new HashSet<>();

		queue.offer(s);
		seen.add(s);

		int min = s.length();
		while(!queue.isEmpty()) {
			s = queue.poll();
			for (String sub : dict) {
				int found = s.indexOf(sub);
				
				// 重复找当前sub是否存在
				while (found != -1) {
					String new_s = s.substring(0, found) + s.substring(found + sub.length(), s.length());
					if (!seen.contains(new_s)) {
						min = Math.min(min, new_s.length());
						queue.offer(new_s);
						seen.add(new_s);
					}
					found = s.indexOf(sub, found + 1);
				}
			}
		}
		return min;
	}
}
