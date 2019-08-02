// 给定一个非空的特别的结点包含非负值二叉树，其中树中的每一个节点包含正好两个或者零个子结点。
// 如果这个结点有两个子结点，那么这个结点的值不大于它的两个子结点。
// 对于这样一个二叉树，你需要输出由整个树当中的结点值构成的集合中的次小值。
// 如果不存在这样的一个次小值，输入-1作为替代。
// lint1094

// Example
// 样例1:

// 输入: 
//     2
//    / \
//   2   5
//      / \
//     5   7

// 输出: 5
// 解释: 最小的值是2，次小值是5.

public class SecMinNodeInBinaryTree {
  public int findSecondMinimumValue(TreeNode root) {
    if (root == null || (root.left == null && root.right == null))
      return -1;

    int left = root.left.val;
    if (left == root.val)
      left = findSecondMinimumValue(root.left);

    int right = root.right.val;
    if (right == root.val)
      right = findSecondMinimumValue(root.right);

    if (left == -1) return right;
    if (right == -1) return left;

    return Math.min(left, right);
  }
}