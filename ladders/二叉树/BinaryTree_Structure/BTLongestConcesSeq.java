/**
 * 给一棵二叉树，找到最长连续路径的长度。 3-4-5
 * 这条路径是指 任何的节点序列中的起始节点到树中的任一节点都必须遵循 父-子 联系。
 * 最长的连续路径必须是从父亲节点到孩子节点（不能逆序）。
 */

// 就是root 是3， root.left=4, 算合法可+1
public class BTLongestConsecSeq {
  private int max = 0;

  public int longestConsecutive(TreeNode root) {
    search(root, 1);
    return max;
  }
  
  private void search(TreeNode node, int cur_height) {
    if (node == null) return;
    
    max = Math.max(max, cur_height);
    
    // go left
    if (node.left != null && node.val + 1 == node.left.val)
      search(node.left, cur_height + 1);
    else
      search(node.left, 1);
    
    // go right
    if (node.right != null && node.val + 1 == node.right.val)
      search(node.right, cur_height + 1);
    else
      search(node.right, 1);
  }
}
