/**
 * https://www.lintcode.com/problem/add-and-search-word-data-structure-design
 * 
 * 设计一个包含下面两个操作的数据结构：
 * addWord(word), search(word)
 * addWord(word)会在数据结构中添加一个单词。
 * 而search(word)则支持普通的单词查询或是只包含.和a-z的简易正则表达式的查询。
 * 一个 . 可以代表一个任何的字母。
 */

public class WordDictionary {
  private TrieNode root = new TrieNode();

  public void addWord(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      int index = word.charAt(i) - 'a';
      if (current.children[index] == null) current.children[index] = new TrieNode();
      current = current.children[index];
    }
    current.isWord = true;
  }

  public boolean search(String word) {
    return find(word, 0, root);
  }

  // DFS definition: word, search start index, current node
  private boolean find(String word, int index, TrieNode currentNode) {
    if (index == word.length()) return node.isWord;
    char currentChar = word.charAt(index);
	
    if (currentChar != '.') {
      int pos = currentChar - 'a';
      if (currentNode.children[pos] == null) return false;
      return find(word, index + 1, currentNode.children[pos]);
		}
		
		if (currentChar == '.') {
      for (TrieNode child : currentNode.children) {
				if (child != null && find(word, index + 1, child)) return true;
      }
		}
		
    return false;
  }
}

class TrieNode {
  TrieNode[] children;
  boolean isWord;

  public TrieNode() {
    this.children = new TrieNode[26];
    this.isWord = false;
  }
}
