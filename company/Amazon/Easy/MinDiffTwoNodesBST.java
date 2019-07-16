// 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
// 二叉树的大小范围在 2 到 100。
// 二叉树总是有效的，每个节点的值都是整数，且不重复

// https://www.lintcode.com/problem/minimum-distance-between-bst-nodes/

class ResultType {
  int max;
  int min;
  public ResultType(int max, int min) {
    this.max = max;
    this.min = min;
  }
}

public class MinDiffTwoNodesBST {
  private int min_diff = Integer.MAX_VALUE;

  public int minDiffInBST(TreeNode root) {
    traverse(root);
    return min_diff;
  }

  private ResultType traverse(TreeNode node) {
    if (node == null) return null;

    ResultType left = traverse(node.left);
    ResultType right = traverse(node.right);

    if (left != null) min_diff = Math.min(min_diff, node.val - left.max);
    if (right != null) min_diff = Math.min(min_diff, right.min - node.val);

    int current_min = left != null ? left.min : node.val;
    int current_max = right != null ? right.max : node.val;

    return new ResultType(current_max, current_min);
  }
}
