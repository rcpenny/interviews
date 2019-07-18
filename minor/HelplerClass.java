// 确定状态:
// 转移方程:
// 初态边界:
// 计算顺序:

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class Interval {
  int start, end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
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

public class HelperClass {}
