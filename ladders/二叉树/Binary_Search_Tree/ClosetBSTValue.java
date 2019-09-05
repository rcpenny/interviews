/**
 * 给一棵非空二叉搜索树以及一个target值，
 * 找到在BST中最接近给定值的节点值
 * 
 *  时间复杂度为 O(h) 注意如果你使用 in-order traversal 的化，
 * 时间复杂度会是 o(n)o(n) 并不是最优的。另外复杂度也不是 O(logn)O(logn) 因为BST 并不保证树高是 logn 的
 */

public class ClosetBSTValue {
  public int closestValue(TreeNode root, double target) {
		if (root == null) return 0;

		int result = root.val;
		double diff = Double.MAX_VALUE;

		while (root != null) {
			double tmpDiff = Math.abs(root.val - target);
			if (tmpDiff < diff) {
				result = root.val;
				diff = tmpDiff;
			}

			if (target > root.val)
				root = root.right;
			else if (target < root.val)
				root = root.left;
		}

		return result;
	}
}
