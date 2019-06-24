/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的距离。
 */
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class MaxDepthBinaryTree {
  public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		int leftHeight = maxDepth(root.left);
		int rightHeight = maxDepth(root.right);

		return Math.max(leftHeight, rightHeight) + 1;
	}
}
