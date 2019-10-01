/**
 * 给定一个二叉查找树(什么是二叉查找树)，
 * 以及一个节点，求该节点在中序遍历的后继，如果没有则返回null
 */

public class BSTInOrderSuccessor {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
		TreeNode successor = null;
		while (root != null) {
			if (target.val >= root.val) {
				root = root.right;
			} else if (target.val < root.val){
				successor = root;
				root = root.left;
			}
		}
		return successor;
	}
}

/**
 * 使用递归实现:
 * 如果根节点小于或等于要查找的节点, 直接进入右子树递归
 * 如果根节点大于要查找的节点, 则暂存左子树递归查找的结果, 
 * 如果是 null, 则直接返回当前根节点; 反之返回左子树递归查找的结果.
 * 
 *      5
 *     / \
 *    3   8
 *   / \ / \
 *  1  4 6  9
 */
