// 0. 最后一步，子问题
// 1. 确定状态
// 2. 转移方程
// 3. 初态边界  
// 4. 顺序计算

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
