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

/**
	 When we turn a simple tree upside down: 
				Root                Left
				/  \                /  \
			Left  Right        Right  Root
	*/

//leet156 此题设计的真好
class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		return helper(null, root);
	}

	private TreeNode helper(TreeNode parent, TreeNode node) {
		if (node == null) return parent; // 得到新的root node

		TreeNode newRoot = helper(node, node.left);

		if (parent != null) {
			node.left = parent.right;
		} else {
			node.left = null;
		}
		node.right = parent;

		return newRoot;
	}
}
