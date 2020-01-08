/**
 * 给一棵非空二叉搜索树以及一个target值，
 * 找到在BST中最接近给定值的节点值
 */

public class ClosetBSTValue {
  public int closestValue(TreeNode root, double target) {
    int closest = 0;
    double diff = Double.MAX_VALUE;

    while (root != null) {
      if (Math.abs(root.val - target) < diff) {
        diff = Math.abs(root.val - target);
        closest = root.val;
      }

      if (target < root.val) root = root.left;
      else root = root.right;
    }

    return closest;
  }
}
