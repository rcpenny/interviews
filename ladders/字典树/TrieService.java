import java.util.Collections;
import java.util.Map;

// https://www.lintcode.com/problem/trie-service/

public class TrieNode {
	public Map<Character, TrieNode> children;
	public List<Integer> top10;

	public TrieNode() {
	children = new TreeMap<Character, TrieNode>();
	top10 = new ArrayList<Integer>();
	}
}

// 这道题就是练习用map写个Trie
public class TrieService {
	private TrieNode root = null;

	public TrieService() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	// <"abc", 2>
	public void insert(String word, int frequency) {
		TrieNode current = getRoot();
		for (int i = 0; i < word.length(); i++) {
			char tmp = word.charAt(i);
			if (!current.children.containsKey(tmp))
				current.children.put(tmp, new TrieNode());
			
			current = current.children.get(tmp);

			// https://www.jiuzhang.com/solution/trie-service/ add这步可以优化
			current.top10.add(frequency);
			Collections.sort(current.top10, Collections.reverseOrder());
			if (current.top10.size() == 11)
				current.top10.remove(current.top10.size() - 1);
		}
	}
}
