/**
 * https://www.lintcode.com/problem/invert-binary-tree/
 */

 public class InvertBinaryTree {
  public void invertBinaryTree(TreeNode root) {
		if (root == null) return;

		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		invertBinaryTree(root.left);
		invertBinaryTree(root.right);
	}
}
