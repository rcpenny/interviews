// 给定一颗二叉树，您需要计算树的直径长度。 
// 二叉树的直径是树中任意两个节点之间最长路径的长度。 此路径不一定会通过树根。
// 给定一棵二叉树 
//           1
//          / \
//         2   3
//        / \     
//       4   5    
// 返回3, 这是路径[4,2,1,3] 或者 [5,2,1,3]的长度.

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

class ResultType {
	int depth;     // max depth at this node
	int diameter;  // max diameter at this node
	
	ResultType (int depth, int diameter) {
		this.depth = depth;
		this.diameter = diameter;
	}
}

// 加入ResultType分治，递归时让我觉得node的状态更加清晰
public class DiameterBinaryTree {
	public int diameterOfBinaryTree(TreeNode root) {
		ResultType result = helper(root);
		return result.diameter;
	}

	private ResultType helper(TreeNode node) {
		if (node == null) return new ResultType(0, 0);
		ResultType left = helper(node.left);
		ResultType right = helper(node.right);

		int node_depth = Math.max(left.depth, right.depth) + 1;
		int larger_children_diameter = Math.max(left.diameter, right.diameter);
		int node_diameter = Math.max(larger_children_diameter, left.depth + right.depth);

		return new ResultType(node_depth, node_diameter);
	}
}
