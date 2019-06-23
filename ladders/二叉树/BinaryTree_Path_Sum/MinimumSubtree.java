/**
 * https://www.lintcode.com/problem/minimum-subtree/
 * 
 * 给一棵二叉树, 找到和为最小的子树, 返回其根节点。
 * 也可以用result type试着做一下
 */

 public class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class MinimumSubtree {
	private TreeNode minNode = null;
	private int minSum = Integer.MAX_VALUE;

  public TreeNode findSubtree(TreeNode root) {
		traverse(root);
		return minNode;
	}

	private int traverse(TreeNode node) {
		if (node == null) return 0;
		int sum = traverse(node.left) + traverse(node.right) + node.val;
		if (sum < minSum) {
			minSum = sum;
			minNode = node;
		}
		return sum;
	}
}
