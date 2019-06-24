class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class MinDepthBinaryTree {
  public int minDepth(TreeNode root) {
		if (root == null) return 0;
		int left = minDepth(root.left);
		int right = minDepth(root.right);

		if (left == 0) return right + 1;
		if (right == 0) return left + 1;
		return Math.min(left, right) + 1;
	}
}
