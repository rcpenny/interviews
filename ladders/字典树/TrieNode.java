/** 字典树模板 */

class TrieNode {
	private TrieNode[] children; // 这个是字典数的精华，其余是辅助
	public boolean isWord;       // 一般也需要
	public String word;          // optional

	public TrieNode() {
		this.children = new TrieNode[26];
		isWord = false;
	}

	// 添加word loop写法
	public void insert(String word) {
		TrieNode current = this;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';               // 得到单词中当前字符的序号
			if (current.children[index] != null) continue;
			current.children[index] = new TrieNode();       // 不存在，在对应位置新建TrieNode
			current = current.children[index];
		}
		current.isWord = true;
		current.word = word;
	}

	// 搜索word loop写法
	public boolean search(String word) {
		TrieNode current = this;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) return false;
			current = current.children[index];
		}
		return current.isWord;
	}

	// 寻找prefix(与search word一样)
	public boolean startWith(String prefix) {
		TrieNode current = this;
		for (int i = 0; i < prefix.length(); i++) {
			int index = prefix.charAt(i) - 'a';
			if (current.children[index] == null) return false;
			current = current.children[position];
		}
		return true;
	}
}
