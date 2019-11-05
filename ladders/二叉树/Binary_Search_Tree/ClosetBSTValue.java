/**
 * 给一棵非空二叉搜索树以及一个target值，
 * 找到在BST中最接近给定值的节点值
 * 
 *  时间复杂度为 O(h) 注意如果你使用 in-order traversal 的化，
 * 时间复杂度会是 o(n)o(n) 并不是最优的。另外复杂度也不是 O(logn)O(logn) 因为BST 并不保证树高是 logn 的
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
