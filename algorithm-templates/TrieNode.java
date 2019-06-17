import sun.security.krb5.internal.Ticket;

/**
 * Implement Trie - 代码模板
 * 需要一个新的类 TrieNode 代表 Trie 中的节点
 * Insert 插入一个单词
 */
public class TrieNode {
	public TrieNode[] children;
	public boolean isWord;
	public String word;

	public TrieNode() {
		this.children = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			this.children[i] = null;
		}
		isWord = false;
	}

	public static void insert(TrieNode p, String word) {
		char[] s = word.toCharArray();
		for (int i = 0; i < s.length; i++) {
			int c = s[i] - 'a';
			if (p.children[c] == null) {
				p.children[c] = new TrieNode();
			}

			p = p.children[c];
		}

		p.isWord = true;
		p.word = word;
	}
}
