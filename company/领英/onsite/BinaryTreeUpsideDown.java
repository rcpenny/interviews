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

// 用分治法的思想，把大部分工作交给“左下级”（left child）去做，自己只做自己分内的工作
// 最终的结果是自下而上的转换

class Solution {
	/**
			When we turn a simple tree upside down: 
							Root                Left
							/  \                /  \
					 Left  Right        Right  Root
			So recursively go to the most left child. Since that will be our new root. And we populate the that child all the way up. 
			So it's visiting root -> root.left -> root.left.left -> root.left.left.left....
			And when its at current root, we make root.left.left = right. root.left.right = root. 
			(We are basically assign left and right child to root.left - new root). Then delete root.left and root.right
				 1
				/ \   root=2 now            root=1 now
			 2   3    ---->      4   1   ------------>   4
			/ \                 / \ / \                 / \
		 4   5               5   2   3               5   2
					(deleted 2's original 4 and 5)            / \
																									 3   1(deleted 1's original 2 and 3)
	*/
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		// exit: root为Null 或者 root是leaf
		if (root == null) return root;
		if (root.left == null && root.right == null) return root;

		TreeNode newRoot = upsideDownBinaryTree(root.left);

		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;

		return newRoot;
	}
}
