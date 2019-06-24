/**
 * https://www.lintcode.com/problem/kth-smallest-element-in-a-bst/
 * 在 BST iterator里用stack做这题
 */
public class KthSmallestElemBST {
	private int result = 0;
	private int count = 0;

  public int kthSmallest(TreeNode root, int k) {
		inOrderTraverse(root, k);
		return result;
	}

	private void inOrderTraverse(TreeNode node, int k) {
		if (node == null) return;

		inOrderTraverse(node.left, k);
		count = count + 1;
		if (count > k) return;
		if (count == k) result = node.val;
		inOrderTraverse(node.right, k);
	}
}
