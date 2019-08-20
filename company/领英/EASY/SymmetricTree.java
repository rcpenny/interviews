// 给定二叉树，返回它是否是自身的镜像（即这棵二叉树是否对称）

// 输入: {1,2,2,3,4,4,3}
// 输出: true
// 解释:
//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// {1,2,2,3,4,4,3}这棵二叉树是对称的

public class SymmetricTree {
	private boolean isSym = true;

  public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;

		return mirrorCompare(root.left, root.right);
	}
	
	//镜像操作精华：深入tree的下一层时，一个向左，一个向右
	private boolean mirrorCompare(TreeNode a, TreeNode b) {
		if (!isSym) return isSym;
		if (a == null && b == null) return true;
		if (a == null || b == null) return false;

		if (a.val != b.val) return false;

		return mirrorCompare(a.left, b.right) && mirrorCompare(a.right, b.left);
	}
}

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}
