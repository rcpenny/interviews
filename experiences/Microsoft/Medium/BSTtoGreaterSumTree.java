// https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
// 更新这个node.val为原BST中所有大于他的数字的和

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

public class BSTtoGreaterSumTree {
  int cur_sum = 0;

  public TreeNode bstToGst(TreeNode root) {
    inorder(root);
    return root;
  }

  // in order traverse, right -> parent -> left
  private void inorder(TreeNode node) {
    if (node == null) return;

    inorder(node.right);
    cur_sum = cur_sum + node.val;
    node.val = cur_sum;
    inorder(node.left);
  }
}
// 遍历法解决问题的思路
// 通过前序/中序/后序的某种遍历，游走整棵树，通过一个全局变量或者传递的参数来记录这个过程中所遇到的点和需要计算的结果。