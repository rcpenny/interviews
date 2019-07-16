// Given a binary tree, find the maximum path sum.
// The path may start and end at any node in the tree.

public class BinaryTreeMaxPathSum {
	private int max = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		traverse(root);
		return max;
	}

	private int traverse(TreeNode node) {
		if (node == null) return 0;

		int left_sum = traverse(node.left);
		int right_sum = traverse(node.right);

		int larger_sum = Math.max(left_sum, right_sum);

		// 忘了这个单node的情况 shoot...
		max = Math.max(max, node.val);
		max = Math.max(max, larger_sum + node.val);
		max = Math.max(max, left_sum + right_sum + node.val);

		return larger_sum + node.val;
	}
}
