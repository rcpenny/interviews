// 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
// 二叉搜索树保证具有唯一的值。

public class RangeSumOfBST {
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) return 0;

		int sum = 0;
		if (L <= root.val && root.val <= R)
			sum += root.val;
		
		if (root.val > L)
			sum += rangeSumBST(root.left, L, R);

		if (root.val < R)
			sum += rangeSumBST(root.right, L, R);

		return sum;
	}
}
