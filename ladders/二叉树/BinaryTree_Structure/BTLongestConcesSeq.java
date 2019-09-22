/**
 * 给一棵二叉树，找到最长连续路径的长度。 3-4-5
 * 这条路径是指 任何的节点序列中的起始节点到树中的任一节点都必须遵循 父-子 联系。
 * 最长的连续路径必须是从父亲节点到孩子节点（不能逆序）。
 */

class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}

// 就是root 是3， root.left=4, 算合法可+1
public class BTLongestConsecSeq {
  private int longestSeq = 0;

  public int longestConsecutive(TreeNode root) {
    helper(root, 1);
    return longestSeq;
  }

  private void helper(TreeNode node, int curLength) {
    if (node == null) return;
    longestSeq = Math.max(curLength, longestSeq);

    if (node.left == null || node.left.val != node.val + 1) {
      helper(node.left, 1);
    } else {
      helper(node.left, curLength + 1);
    }
    
    if (node.right == null || node.right.val != node.val + 1) {
      helper(node.right, 1);
    } else {
      helper(node.right, curLength + 1);
    }
  }
}