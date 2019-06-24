/**
 * https://www.lintcode.com/problem/flatten-binary-tree-to-linked-list/
 * 
 * 将一棵二叉树按照前序遍历拆解成为一个 假链表。
 * 所谓的假链表是说，用二叉树的 right 指针，来表示链表中的 next 指针。
 */
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class FlattenBinaryTreeToLinkedList {

  public void flatten(TreeNode root) {
		if (root == null) return;

		if (root.left != null) {
			TreeNode lastNode = leftTreeLastNode(root.left);
			//将右子树append到这个node 左右子树交换，左子树为null
			lastNode.right = root.right;
			root.right = root.left;
			root.left = null;
		}

		flatten(root.right);
	}

	//左子树非null的话，找到左子树前序遍历的最后一个node
	private TreeNode leftTreeLastNode(TreeNode node) {
		while (node.right != null) node = node.right;
		return node;
	}
}
