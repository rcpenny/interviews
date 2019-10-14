import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 给出两个单词（start和end）和一个字典，找出从start到end的最短转换序列，输出最短序列的长度。
 * 变换规则如下：
 * 每次只能改变一个字母
 * 变换过程中的中间单词必须在字典中出现。(起始单词和结束单词不需要出现在字典中)
 * 
 * 如果不存在这样的转换序列，返回 0
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 */

 // use 2-direction BFS
public class WordLadder {
	// 这种字母变化是否出现过
	Set<String> seen = new HashSet<>();

  public int ladderLength(String start, String end, Set<String> dict) {
		// 把end补入dict b/c 因为结束单词不需要出现在字典中
		dict.add(end);
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		seen.add(start);

		int steps = 1;
		while (!queue.isEmpty()) {
			//求steps 分层
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(end)) return steps;

				List<String> transformations = getTransformations(cur, seen);
				
				for (String trans : transformations) {
					if (dict.contains(trans)) {
						queue.offer(trans);
						dict.remove(trans);
					}
				}
			}
			steps++;
		}

		return 0;
	}

	// 放seen，所有dict的用过的，transform出来过的都扔进去
	private List<String> getTransformations(String cur, Set<String> seen) {
		List<String> transformations = new ArrayList<>();
		for (int i = 0; i < cur.length(); i++) {
			for (int j = 0; j < 26; j++) {
				char[] array = cur.toCharArray();
				array[i] = (char) ('a' + j);
				String tmp = String.valueOf(array);
				if (seen.contains(tmp)) continue;
				seen.add(tmp);
				transformations.add(tmp);
			}
		}
		return transformations;
	}
}
