/**
 * 给定一个二叉树,确定它是高度平衡的。
 * 对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。 
 */

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

class ResultType {
	boolean balanced;
	int height;
	public ResultType(boolean balanced, int height) {
		this.balanced = balanced;
		this.height = height;
	}
}

public class BalancedBinaryTree {

  public boolean isBalanced(TreeNode root) {
		ResultType result = getTreeProperties(root);
		return result.balanced;
	}

	private ResultType getTreeProperties(TreeNode node) {
		if (node == null) return new ResultType(true, 0);

		ResultType leftTree = getTreeProperties(node.left);
		ResultType rightTree = getTreeProperties(node.right);

		int treeHeight = Math.max(leftTree.height, rightTree.height) + 1;
		int heightDiff = Math.abs(leftTree.height - rightTree.height);
		boolean balanced = leftTree.balanced && rightTree.balanced && heightDiff <= 1;
		
		return new ResultType(balanced, treeHeight);
	}


	// 不用ResultType，全局变量balanced, 递归method return height就好
	private boolean balanced = true;
	public boolean isBalanced2(TreeNode root) {
		height(root);
		
		return balanced;
	}
	
	private int height(TreeNode node) {
		if (!balanced || node == null) return 0;
		
		int left_height = height(node.left);
		int right_height = height(node.right);
		
		if (Math.abs(left_height - right_height) > 1) {
			balanced = false;
		}
		
		return Math.max(left_height, right_height) + 1;
	}
}
