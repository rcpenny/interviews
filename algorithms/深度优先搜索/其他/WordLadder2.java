import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列。
		变换规则如下：

		每次只能改变一个字母。
		变换过程中的中间单词必须在字典中出现。

		输入：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
		输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
		解释：
		1."hit"->"hot"->"dot"->"dog"->"cog"
		2."hit"->"hot"->"lot"->"log"->"cog"
		第一个序列的字典序小于第二个。

	Notice
	所有单词具有相同的长度。
	所有单词都只包含小写字母。
	题目确保存在合法的路径。
 */

 /**
	* 1. BFS找到最短路径，并且把tree build起来
	* 2. 使用DFS在tree中找出所有的ladders
	*/

public class WordLadder2 {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		List<List<String>> ladders = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, Integer> distance = new HashMap<String, Integer>();

		dict.add(start);
		dict.add(end);

		bfs(map, distance, end, start, dict);
			
		List<String> path = new ArrayList<String>();
			
		dfs(ladders, path, start, end, distance, map);

		return ladders;
	}

	private void dfs(List<List<String>> ladders, List<String> path, String crt, String end, Map<String, Integer> distance, Map<String, List<String>> map) {
			path.add(crt);
			if (crt.equals(end)) {
					ladders.add(new ArrayList<String>(path));
			} else {
					for (String next : map.get(crt)) {
							if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { 
									dfs(ladders, path, next, end, distance, map);
							}
					}           
			}
			path.remove(path.size() - 1);
	}

	void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
					String start, String end, Set<String> dict) {
			Queue<String> q = new LinkedList<String>();
			q.offer(start);
			distance.put(start, 0);
			for (String s : dict) {
					map.put(s, new ArrayList<String>());
			}
			
			while (!q.isEmpty()) {
					String crt = q.poll();

					List<String> nextList = expand(crt, dict);
					for (String next : nextList) {
							map.get(next).add(crt);
							if (!distance.containsKey(next)) {
									distance.put(next, distance.get(crt) + 1);
									q.offer(next);
							}
					}
			}
	}

	List<String> expand(String crt, Set<String> dict) {
		List<String> expansion = new ArrayList<String>();

		for (int i = 0; i < crt.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (ch != crt.charAt(i)) {
					String expanded = crt.substring(0, i) + ch
									+ crt.substring(i + 1);
					if (dict.contains(expanded)) {
							expansion.add(expanded);
					}
				}
			}
		}

		return expansion;
	}
}
