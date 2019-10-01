/**
 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)
 * 节点的左子树中的值要严格小于该节点的值。
 * 节点的右子树中的值要严格大于该节点的值。
 */
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

class State {
	int min;
	int max;
	boolean isBST;
	public State(int min, int max, boolean isBST) {
		this.min = min;
		this.max = max;
		this.isBST = isBST;
	}
}

public class ValidateBST {

  public boolean isisBST(TreeNode root) {
		State rootState = getNodeState(root);
		return rootState.isBST;
	}

	private State getNodeState(TreeNode node) {
		if (node == null) return new State(Integer.MAX_VALUE, Integer.MIN_VALUE, true);

		State left = getNodeState(node.left);
		State right = getNodeState(node.right);

		// 先判断isBST为false的情况可以减少计算。
		if (!left.isBST || !right.isBST) return new State(0, 0, false);

		// child是null的情况要判断
		if (left.max >= node.val && node.left != null) return new State(0, 0, false);
		if (right.min <= node.val && node.right != null) return new State(0, 0, false);

		// 得到该点极值，考虑左右子树是否为空
		int currentMax = node.right == null ? node.val : right.max;
		int currentMin = node.left == null ? node.val : left.min;
		return new State(currentMin, currentMax, true);
	}
}
