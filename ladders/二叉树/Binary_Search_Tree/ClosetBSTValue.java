/**
 * 给一棵非空二叉搜索树以及一个target值，
 * 找到在BST中最接近给定值的节点值
 * 
 * 算法很简单，求出 lowerBound 和 upperBound 即 < target 的最大值和 >= target 的最小值
 * 然后在两者之中去比较谁更接近，然后返回即可。
 *  时间复杂度为 O(h) 注意如果你使用 in-order traversal 的化，
 * 时间复杂度会是 o(n)o(n) 并不是最优的。另外复杂度也不是 O(logn)O(logn) 因为BST 并不保证树高是 logn 的
 */
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class ClosetBSTValue {

  public int closestValue(TreeNode root, double target) {
		if (root == null) return 0;
		TreeNode lowerNode = lowerBound(root, target);
		TreeNode upperNode = upperBound(root, target);

		if (lowerNode == null) return upperNode.val;
		if (upperNode == null) return lowerNode.val;

		if (target - lowerNode.val > upperNode.val - target) return upperNode.val;
		return lowerNode.val;
	}

	private TreeNode lowerBound(TreeNode root, double target) {
		if (root == null) return null;
		if (root.val >= target) return lowerBound(root.right, target);
		// root.val < target
		TreeNode lowerNode = lowerBound(root.right, target);
		if (lowerNode != null) return lowerNode;
		return root;
	}
 
	private TreeNode upperBound(TreeNode root, double target) {
		if (root == null) return null;
		if (root.val < target) return upperBound(root.right, target);
		// root.val >= target
		TreeNode upperNode = upperBound(root.left, target);
		if (upperNode != null) return upperNode;
		return root;
	}
}
