import sun.security.krb5.internal.Ticket;

/**
 * Implement Trie - 代码模板
 * 需要一个新的类 TrieNode 代表 Trie 中的节点
 * Insert 插入一个单词
 */
public class TrieNode {
	public TrieNode[] sons;
	public boolean isWord;
	public String word;

	public TrieNode() {
		this.sons = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			this.sons[i] = null;
		}
		isWord = false;
	}

	public static void insert(TrieNode p, String word) {
		char[] s = word.toCharArray();
		for (int i = 0; i < s.length; i++) {
			int c = s[i] - 'a';
			if (p.sons[c] == null) {
				p.sons[c] = new TrieNode();
			}

			p = p.sons[c];
		}

		p.isWord = true;
		p.word = word;
	}
}
