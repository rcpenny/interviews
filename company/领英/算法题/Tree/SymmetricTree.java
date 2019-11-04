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

// lc101

public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
		return mirrorCompare(root, root);
	}
	
	//镜像操作精华：深入tree的下一层时，一个向左，一个向右
	private boolean mirrorCompare(TreeNode a, TreeNode b) {
		if (a == null && b == null) return true;
		if (a == null || b == null) return false;
		return a.val == b.val && mirrorCompare(a.left, b.right) && mirrorCompare(a.right, b.left);
	}
}
