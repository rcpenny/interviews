/**
 * 给一棵二叉树，找到有最大平均值的子树。返回子树的根结点。
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
	int size;
	int sum;
	public ResultType(int size, int sum) {
		this.size = size;
		this.sum = sum;
	}
}

public class SubtreeWithMaxAvg {
	private double maxAvg = Double.NEGATIVE_INFINITY;
	private TreeNode maxNode;

  public TreeNode findSubtree2(TreeNode root) {
		findState(root);
		return maxNode;
	}

	private ResultType findState(TreeNode node) {
		if (node == null) return new ResultType(0, 0);

		ResultType left = findState(node.left);
		ResultType right = findState(node.right);

		int size = left.size + right.size + 1;
		int sum = left.sum + right.sum + node.val;

		if ((double) sum / size > maxAvg) {
			maxAvg = (double) sum / size;
			maxNode = node;
		} 

		return new ResultType(size, sum);
	}
}
