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

// 用分治法的思想，把大部分工作交给“左下级”（left child）去做，自己只做自己分内的工作。最终的结果是自下而上的转换。

// 自己分内的工作是：
// change its right sibling to its left child;
// change its parent to its right child
// clean up parent's children

public class BinaryTreeUpsideDown {
  public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null) return null;

		TreeNode newRoot = helper(root);

		return newRoot;
	}

	private TreeNode helper(TreeNode node)  {
		if (node.left == null) return node;

		TreeNode newRoot = helper(node.left);
		node.left.right = node;
		node.left.left = node.right;

		node.left = null;
		node.right = null;

		return newRoot;
	}
}
