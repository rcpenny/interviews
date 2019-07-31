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

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

// 此题只需要比较值，resultType大才小用
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;

		return helper(root.left, root.right);
	}
	
	private boolean helper(TreeNode left, TreeNode right) {
		if (left == null && right == null) return true;
		if (left == null || right == null) return false;
		if (left.val != right.val) return false;
		
		// 这步binary tree镜像操作是精华
		return helper(left.left, right.right) && helper(left.right, right.left);
	}
}
