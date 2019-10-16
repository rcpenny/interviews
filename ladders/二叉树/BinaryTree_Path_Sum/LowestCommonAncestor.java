/** 
 * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)
 * 最近公共祖先是两个节点的公共的祖先节点且具有最大深度
 * 
 * 假设给出的两个节点都在树中存在
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
	TreeNode ancestor;
	ResultType (boolean afound, boolean bfound, TreeNode node) {
		this.afound = afound;
		this.bfound = bfound;
		this.ancestor = node;
	}
}

public class LowestCommonAncestor {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
		return helper(root, A, B).ancestor;
	}

	private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) return new ResultType(false, false, null);

		ResultType left = helper(root.left, a, b);
		ResultType right = helper(root.right, a, b);

		// 左右子树 其中某个已经有LCA了
		if (left.ancestor != null) return left;
    if (right.ancestor != null) return right;

		// 还没找到LCA,说明 A B中至少有一个没有找到,与node的值合并
		boolean afound = left.afound || right.afound || root.val == a.val;
		boolean bfound = left.bfound || right.bfound || root.val == b.val;
		TreeNode node = (afound && bfound) ? root : null;

		return new ResultType(afound, bfound, node);
	}
}
