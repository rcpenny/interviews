/**
 * Implement Trie - 代码模板
 * 需要一个新的类 TrieNode 代表 Trie 中的节点
 * Insert 插入一个单词
 * 
 * Trie 的考点
 * 实现一个 Trie
 * 比较 Trie 和 Hash 的优劣
 * 字符矩阵类问题使用 Trie 比 Hash 更高效
 * 
 * here we use for loops, we can also use recursion.
 * https://github.com/rcpenny/interviews/blob/master/submissions/Trie/Trie.java
 */
class TrieNode {
	private TrieNode[] children;
	public boolean isWord;
	public String word;

	public TrieNode() {
		this.children = new TrieNode[26];
		isWord = false;
	}

	public void insert(String word) {
		TrieNode current = this;
		for (int i = 0; i < word.length(); i++) {
			int position = word.charAt(i) - 'a';
			if (current.children[position] == null) {
				current.children[position] = new TrieNode();
			}
			current = current.children[position];
		}
		current.isWord = true;
		current.word = word;
	}

	public boolean search(String word) {
		TrieNode current = this;
		for (int i = 0; i < word.length(); i++) {
			int position = word.charAt(i) - 'a';
			if (current.children[position] == null) {
				return false;
			}
			current = current.children[position];
		}
		return true;
	}

	public boolean startWith(String prefix) {
		TrieNode current = this;
		for (int i = 0; i < prefix.length(); i++) {
			int position = prefix.charAt(i) - 'a';
			if (current.children[position] == null) {
				return false;
			}
			current = current.children[position];
		}
		return true;
	}

	/**
	 * 字典树Trie总结
	 * – 合并所有公共的前缀
	 * – 动态插入与查询单词
	 * 不能查询非前缀（如字符串一部分）
	 */
}
