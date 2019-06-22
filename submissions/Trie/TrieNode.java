
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


}
