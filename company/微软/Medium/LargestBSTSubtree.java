// Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
// where largest means subtree with largest number of nodes in it.
// The return value is the subtree's size

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

class ResultType {
	int max;
	int min;
	boolean isBST;
	int size;
	ResultType(int max, int min, boolean isBST, int size) {
		this.max = max;
		this.min = min;
		this.isBST = isBST;
		this.size = size;
	}
}

public class LargestBSTSubtree {

  public int largestBSTSubtree(TreeNode root) {
		return getNodeState(root).size;
	}

	private ResultType getNodeState(TreeNode node) {
		if (node == null) return new ResultType(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);

		ResultType left = getNodeState(node.left);
		ResultType right = getNodeState(node.right);

		if (!left.isBST || !right.isBST) return new ResultType(0, 0, false, Math.max(left.size, right.size));

		if ((left.max >= node.val && left != null) || (right.min <= node.val && right != null)) 
			return new ResultType(0, 0, false, Math.max(left.size, right.size));

		// 还是记得判断left right child是否存在
		int currentMax = node.right == null ? node.val : right.max;
		int currentMin = node.left == null ? node.val : left.min;
		return new ResultType(currentMax, currentMin, true, left.size + right.size + 1);
	}
}
