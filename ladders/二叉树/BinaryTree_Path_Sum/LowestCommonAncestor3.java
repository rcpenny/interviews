/**
 * https://www.lintcode.com/problem/lowest-common-ancestor-iii/
 * 
 * 给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。
 * 两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。
 * 返回 null 如果两个节点在这棵树上不存在最近公共祖先的话。
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
	boolean afound;
	boolean bfound;
	TreeNode node;
	ResultType(boolean afound, boolean bfound, TreeNode node) {
		this.afound = afound;
		this.bfound = bfound;
		this.node = node;
	}
}

public class LowestCommonAncestor3 {
  public TreeNode lowestCommonAncesto3(TreeNode root, TreeNode A, TreeNode B) {
		ResultType result = helper(root, A, B);
		if (result.afound && result.bfound) return reuslt.node;
		return null;
	}

	private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) return new ResultType(false, false, root);

		ResultType left = helper(root.left, a, b);
		ResultType right = helper(root.right, a, b);

		// 左右子树 其中某个已经是LCA了
		if (left.afound && left.bfound) return left;
		if (right.afound && right.bfound) return right;

		// 还没找到LCA,说明 A B中至少有一个没有找到
		boolean afound = left.afound || right.afound || root.val == a.val;
		boolean bfound = left.bfound || right.bfound || root.val == b.val;
		return new ResultType(afound, bfound, root);
	}
}
