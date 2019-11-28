// https://www.lintcode.com/problem/implement-trie-prefix-tree
// 实现一个 Trie，包含 insert, search, 和 startsWith 这三个方法

public class Trie {
  private TrieNode root;

  public Trie() {
    this.root = new TrieNode();
  }

  public void insert(String word) {
    root.insert(word, 0);
  }

  public boolean search(String word) {
    TrieNode node = root.find(word, 0);
    return (node != null && node.isWord);
  }

  public boolean startsWith(String prefix) {
    TrieNode node = root.find(prefix, 0);
    return node != null;
  }
}

class TrieNode {
  private TrieNode[] children;
  public boolean isWord;

  public TrieNode() {
    this.children = new TrieNode[26];
    this.isWord = false;
  }

	// 插入word, 递归写法
  public void insert(String word, int index) {
    if (index == word.length()) {
      isWord = true;
      return;
    }

    int position = word.charAt(index) - 'a';
    if (children[position] == null) children[position] = new TrieNode();
    children[position].insert(word, index + 1);
  }

	// 寻找word, 递归写法
  public TrieNode find(String word, int index) {
		if (index == word.length()) return this;
		
    int position = word.charAt(index) - 'a';
    if (children[position] == null) return null;
    return children[position].find(word, index + 1);
  }
}
