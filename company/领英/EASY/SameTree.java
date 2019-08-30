// 检查两棵二叉树是否等价。等价的意思是说，首先两棵二叉树必须拥有相同的结构，并且每个对应位置上的节点上的数都相等

public class SameTree {
  public boolean isIdentical(TreeNode a, TreeNode b) {
    if (a == null && b == null) return true;
    if (a == null || b == null) return true;

    return a.val == b.val && isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
  }
}
