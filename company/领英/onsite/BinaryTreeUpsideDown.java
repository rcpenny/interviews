// Given a binary tree where all the right nodes are either leaf nodes with a sibling 
// (a left node that shares the same parent node) or empty, 
// flip it upside down and turn it into a tree where the original right nodes 
// turned into left leaf nodes. Return the new root.

// The input is
//     1
//    / \
//   2   3
//  / \
// 4   5

// and the output is
//    4
//   / \
//  5   2
//     / \
//    3   1

//lc156 此题设计的真好
class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		// exit: root为Null 或者 root是leaf
		if (root == null) return root;
		if (root.left == null && root.right == null) return root;

		// 1. 一直朝左下走，new root一直就是left most node了
		TreeNode newRoot = upsideDownBinaryTree(root.left);

		// 从left most 往上折返离开递归时，进行如是操作

		/**
		   When we turn a simple tree upside down: 
						Root                Left
						/  \                /  \
					Left  Right        Right  Root
		 */

		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;

		return newRoot;
	}
}
